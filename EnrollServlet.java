import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Get courseId from URL parameter
        String courseId = request.getParameter("courseId");

        // 2. Simulate available courses (same list as DashboardServlet)
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(new Course("101", "Web Programming", "Dr. Silva"));
        allCourses.add(new Course("102", "Data Structures", "Prof. Perera"));
        allCourses.add(new Course("103", "Operating Systems", "Dr. Fernando"));

        // 3. Find the selected course
        Course selectedCourse = null;
        for (Course course : allCourses) {
            if (course.getCourseId().equals(courseId)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse != null) {
            // 4. Get the session
            HttpSession session = request.getSession();
            List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
            
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            // Avoid duplicates
            boolean alreadyEnrolled = false;
            for (Course c : enrolledCourses) {
                if (c.getCourseId().equals(selectedCourse.getCourseId())) {
                    alreadyEnrolled = true;
                    break;
                }
            }

            if (!alreadyEnrolled) {
                enrolledCourses.add(selectedCourse);
                session.setAttribute("enrolledCourses", enrolledCourses);
            }
        }

        // 5. Redirect back to dashboard
        response.sendRedirect("DashboardServlet");
    }
}
