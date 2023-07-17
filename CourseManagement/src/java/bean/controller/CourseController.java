/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controller;

import bean.model.Courses;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import root.DataController;

/**
 *
 * @author hrkas
 */
public class CourseController {

    private static DataController dbCon;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String sql;
    private static String nav;
    public static boolean flag;

    private static final FacesContext context = FacesContext.getCurrentInstance();

    private static ArrayList courseList;

    public CourseController() {
    }

    public static List<Courses> showAllList() {
        try {
            courseList = new ArrayList();
            sql = "SELECT courseCode, courseTitle, programCode, year, semester, units, lechours, labhours FROM courses";

            ps = dbCon.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Courses c = new Courses();
                c.setCourseCode(rs.getString(1));
                c.setCourseTitle(rs.getString(2));
                c.setProgramCode(rs.getString(3));
                c.setYear(rs.getString(4));
                c.setSemester(rs.getString(5));
                c.setUnits(rs.getString(6));
                c.setLecHours(rs.getDouble(7));
                c.setLabHours(rs.getDouble(8));
                courseList.add(c);
            }
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static String addCourse(Courses c) {
        nav = "index";
        sql = "INSERT INTO courses VALUES(?,?,?,?,?,?,?,?,?)";

        UUID uid = UUID.randomUUID();
//        String bin_id = uid.toString();
        try {
            ps = dbCon.getConnection().prepareStatement(sql);

            ps.setBytes(1, uid.toString().getBytes());
            ps.setString(2, c.getCourseCode());
            ps.setString(3, c.getCourseTitle());
            ps.setString(4, c.getProgramCode());
            ps.setString(5, c.getYear());
            ps.setString(6, c.getSemester());
            ps.setString(7, c.getUnits());
            ps.setDouble(8, c.getLecHours());
            ps.setDouble(9, c.getLabHours());
            flag = ps.execute();
            ps.close();
            DataController.con.close();
            context.addMessage(null, new FacesMessage("Saved", "Course"));
            nav = "main";
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(nav);

        return nav;
    }

    public static String updateCourse(Courses c) {
        nav = "index";
        try {

            sql = "UPDATE courses SET courseTile = ?, programCode = ?, year = ?,"
                    + "semester = ?, units = ?, lecHours = ?, labHours = ?"
                    + "WHERE courseCoe = ?";

            ps = dbCon.getConnection().prepareStatement(sql);
            ps.setString(1, c.getCourseTitle());
            ps.setString(2, c.getProgramCode());
            ps.setString(3, c.getYear());
            ps.setString(4, c.getSemester());
            ps.setString(5, c.getUnits());
            ps.setDouble(6, c.getLecHours());
            ps.setDouble(7, c.getLabHours());
            ps.setString(8, c.getCourseCode());
            flag = ps.execute();
            ps.close();
            DataController.con.close();
            context.addMessage(null, new FacesMessage("Saved", "Course"));
            nav = "main";
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nav;
    }

    public static String editCourse(String id) {
        Courses course = new Courses();
        nav = "main";
        try {
            Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sql = "SELECT * FROM courses WHERE coursecode = ?";

            ps = dbCon.getConnection().prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                course.setCourseID(rs.getString(1));
                course.setCourseCode(rs.getString(2));
                course.setCourseTitle(rs.getString(3));
                course.setProgramCode(rs.getString(4));
                course.setYear(rs.getString(5));
                course.setSemester(rs.getString(6));
                course.setUnits(rs.getString(7));
                course.setLecHours(rs.getDouble(8));
                course.setLabHours(rs.getDouble(9));
            }
            sessionMapObj.put("editCourse", course);
            ps.close();
            DataController.con.close();
            nav = "editCourse";
//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nav;
    }

    public static String removeCourse(String id) {
        nav = "index";
        sql = "DELETE FROM courses WHERE courseId = ?";
        try {
            ps = dbCon.getConnection().prepareStatement(sql);
            ps.setString(1, id);
            flag = ps.execute();
            ps.close();
            DataController.con.close();
            context.addMessage(null, new FacesMessage("Deleted", "Course"));
            nav = "main";

//To change body of generated methods, choose Tools | Templates.
        } catch (SQLException ex) {
            Logger.getLogger(CourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nav;
    }
}
