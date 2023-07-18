/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import controllers.util.JsfUtil;
import controllers.util.PagingInfo;
import entities.Student;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import models.StudentFacade;

/**
 *
 * @author hrkas
 */
public class StudentController {

    public StudentController() {
        pagingInfo = new PagingInfo();
        converter = new StudentConverter();
    }
    private Student student = null;
    private List<Student> studentItems = null;
    private StudentFacade jpaController = null;
    private StudentConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "StudentJPAJSFPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public StudentFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (StudentFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "studentJpa");
        }
        return jpaController;
    }

    public SelectItem[] getStudentItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getStudentItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Student getStudent() {
        if (student == null) {
            student = (Student) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStudent", converter, null);
        }
        if (student == null) {
            student = new Student();
        }
        return student;
    }

    public String listSetup() {
        reset(true);
        return "student_list";
    }

    public String createSetup() {
        reset(false);
        student = new Student();
        return "student_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(student);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Student was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("student_detail");
    }

    public String editSetup() {
        return scalarSetup("student_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        student = (Student) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentStudent", converter, null);
        if (student == null) {
            String requestStudentString = JsfUtil.getRequestParameter("jsfcrud.currentStudent");
            JsfUtil.addErrorMessage("The student with id " + requestStudentString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String studentString = converter.getAsString(FacesContext.getCurrentInstance(), null, student);
        String currentStudentString = JsfUtil.getRequestParameter("jsfcrud.currentStudent");
        if (studentString == null || studentString.length() == 0 || !studentString.equals(currentStudentString)) {
            String outcome = editSetup();
            if ("student_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit student. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(student);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Student was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentStudent");
        Integer id = new Integer(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Student was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
//        if ((ERROR)) {
            return relatedControllerOutcome;
//        }
//        return listSetup();
    }

    public List<Student> getStudentItems() {
        if (studentItems == null) {
            getPagingInfo();
            studentItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return studentItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "student_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "student_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        student = null;
        studentItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Student newStudent = new Student();
        String newStudentString = converter.getAsString(FacesContext.getCurrentInstance(), null, newStudent);
        String studentString = converter.getAsString(FacesContext.getCurrentInstance(), null, student);
        if (!newStudentString.equals(studentString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
