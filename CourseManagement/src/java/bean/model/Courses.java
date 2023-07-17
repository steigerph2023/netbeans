/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.model;

import bean.controller.CourseController;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author hrkas
 */
@ManagedBean(name = "course", eager = true)
@SessionScoped
public class Courses implements Serializable{

    /**
     * Creates a new instance of Courses
     */
    
    public List<Courses> courseList;
    
//    @Column(name = "courseId")
    private byte[] courseId;
    private String courseID;
//    @Column(name = "courseCode")
    private String courseCode;
    
//    @Column(name = "courseTitle")
    private String courseTitle;
    
//    @Column(name = "programCode")
    private String programCode;
    
//    @Column(name = "year")
    private String year;

//    @Column(name = "semester")
    private String semester;
   
//    @Column(name = "units")
    private String units;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
//    @Column(name = "lecHours")
    private Double lecHours;
//    @Column(name = "labHours")
    private Double labHours;
    public Courses() {
    }

    public Courses(byte[] courseId, String courseCode, String courseTitle, String programCode, String year, String semester, String units, Double lecHours, Double labHours) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.programCode = programCode;
        this.year = year;
        this.semester = semester;
        this.units = units;
        this.lecHours = lecHours;
        this.labHours = labHours;
    }

    /**
     * @return the courseId
     */
    public byte[] getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(byte[] courseId) {
        byte[] oldCourseId = this.courseId;
        this.courseId = courseId;
        propertyChangeSupport.firePropertyChange(PROP_COURSEID, oldCourseId, courseId);
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        java.lang.String oldCourseCode = this.courseCode;
        this.courseCode = courseCode;
        propertyChangeSupport.firePropertyChange(PROP_COURSECODE, oldCourseCode, courseCode);
    }

    /**
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        java.lang.String oldCourseTitle = this.courseTitle;
        this.courseTitle = courseTitle;
        propertyChangeSupport.firePropertyChange(PROP_COURSETITLE, oldCourseTitle, courseTitle);
    }

    /**
     * @return the programCode
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * @param programCode the programCode to set
     */
    public void setProgramCode(String programCode) {
        java.lang.String oldProgramCode = this.programCode;
        this.programCode = programCode;
        propertyChangeSupport.firePropertyChange(PROP_PROGRAMCODE, oldProgramCode, programCode);
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        java.lang.String oldYear = this.year;
        this.year = year;
        propertyChangeSupport.firePropertyChange(PROP_YEAR, oldYear, year);
    }

    /**
     * @return the semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(String semester) {
        java.lang.String oldSemester = this.semester;
        this.semester = semester;
        propertyChangeSupport.firePropertyChange(PROP_SEMESTER, oldSemester, semester);
    }

    /**
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(String units) {
        java.lang.String oldUnits = this.units;
        this.units = units;
        propertyChangeSupport.firePropertyChange(PROP_UNITS, oldUnits, units);
    }

    /**
     * @return the lecHours
     */
    public Double getLecHours() {
        return lecHours;
    }

    /**
     * @param lecHours the lecHours to set
     */
    public void setLecHours(Double lecHours) {
        java.lang.Double oldLecHours = this.lecHours;
        this.lecHours = lecHours;
        propertyChangeSupport.firePropertyChange(PROP_LECHOURS, oldLecHours, lecHours);
    }

    /**
     * @return the labHours
     */
    public Double getLabHours() {
        return labHours;
    }

    /**
     * @param labHours the labHours to set
     */
    public void setLabHours(Double labHours) {
        java.lang.Double oldLabHours = this.labHours;
        this.labHours = labHours;
        propertyChangeSupport.firePropertyChange(PROP_LABHOURS, oldLabHours, labHours);
    }
    private final transient PropertyChangeSupport propertyChangeSupport = new java.beans.PropertyChangeSupport(this);
    public static final String PROP_COURSEID = "courseId";
    public static final String PROP_COURSECODE = "courseCode";
    public static final String PROP_COURSETITLE = "courseTitle";
    public static final String PROP_PROGRAMCODE = "programCode";
    public static final String PROP_YEAR = "year";
    public static final String PROP_SEMESTER = "semester";
    public static final String PROP_UNITS = "units";
    public static final String PROP_LECHOURS = "lecHours";
    public static final String PROP_LABHOURS = "labHours";
    
    @PostConstruct
    public void init(){
        courseList = CourseController.showAllList();
    }
    
    public List<Courses> showAll(){
        return courseList;
    }
    public String addCourse(Courses c){
        return CourseController.addCourse(c);
    }
    public String updateCourse(Courses c){
        return CourseController.updateCourse(c);
    }
    public String editCourse(String id){
        return CourseController.editCourse(id);
    }
    public String removeCourse(String id){
        return CourseController.removeCourse(id);
    }
}
