package by.talai.web;

import by.talai.service.AutomobileService;
import by.talai.service.RequestService;
import by.talai.service.RideService;
import by.talai.service.UserService;
import by.talai.service.dto.AutomobilesDto;
import by.talai.service.dto.UsersDto;
import by.talai.service.impl.AutomobileServiceImpl;
import by.talai.service.impl.RequestServiceImpl;
import by.talai.service.impl.RideServiceImpl;
import by.talai.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MotorDepotController extends HttpServlet {

    private final AutomobileService automobileService = new AutomobileServiceImpl();
//    private final RideService rideService = new RideServiceImpl();
    private final UserService userService = new UserServiceImpl();
//    private final RequestService requestService = new RequestServiceImpl();

    public MotorDepotController() throws Exception {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (request.getParameter("local") != null) {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
        }


        request.getSession(true).setAttribute("role", "admin");


        String action = request.getServletPath();
//        System.out.println("action = " + action);

        if (action != null) {
            try {
                switch (action) {
                    case "/login":
                        login(request, response);
                        break;
                    case "/logout":
                        logout(request, response);
                        break;
                    case "/admin/registration":
                        goRegisterUser(request, response);
                        break;
                    case "/admin/users":
                        goListUsers(request, response);
                        break;
                    case "/admin/users/c_status":
                        changeUserStatus(request, response);
                        break;
                    case "/user/requests":
                        goListRequests(request, response);
                        break;
                    case "/user/rides":
                        goListRides(request, response);
                        break;
                    case "/user/dispatcher/autos":
                        goListAutomobiles(request, response);
                        break;
                    case "/user/dispatcher/auto-form":
                        goAutomobileForm(request, response);
                        break;
                    case "/user/dispatcher/charterer-form":
                        goChartererForm(request, response);
                        break;
                    case "/user/dispatcher/charterers":
                        goCharterers(request, response);
                        break;
                    case "/user/dispatcher/driver-form":
                        goDriverForm(request, response);
                        break;
                    case "/user/dispatcher/drivers":
                        goListDrivers(request, response);
                        break;
                    case "/user/dispatcher/request-form":
                        goRequestForm(request, response);
                        break;
                    case "/user/dispatcher/maintenances":
                        goListMaintenances(request, response);
                        break;
                    case "/user/driver/auto":
                        goAutomobile(request, response);
                        break;
                    case "/user/driver/repair-request":
                        goRequestRepair(request, response);
                        break;
                    default:
                        goHome(request, response);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ServletException(ex);
                //TODO
            }
        }

    }

    private void changeUserStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("id"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        userService.changeUserStatus(userId, statusId);

        response.sendRedirect("/motor_depot/admin/users");
    }


    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");

        dispatcher.forward(request, response);
    }

    private void goListRides(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rides.jsp");
        dispatcher.forward(request, response);
    }

    private void goRequestRepair(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/repair-request.jsp");
        dispatcher.forward(request, response);
    }

    private void goAutomobile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/auto.jsp");
        dispatcher.forward(request, response);
    }

    private void goListMaintenances(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/maintenance.jsp");
        dispatcher.forward(request, response);
    }

    private void goRequestForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/request-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goListDrivers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp");
        dispatcher.forward(request, response);
    }

    private void goDriverForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goCharterers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterers.jsp");
        dispatcher.forward(request, response);
    }

    private void goChartererForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goAutomobileForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/auto-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goListAutomobiles(HttpServletRequest request, HttpServletResponse response) throws Exception {


        AutomobilesDto automobilesDto = automobileService.getAllAutomobilesDto();

        request.setAttribute("automobilesDto", automobilesDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autos.jsp");
        dispatcher.forward(request, response);
    }

    private void goListRequests(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/requests.jsp");
        dispatcher.forward(request, response);
    }

    private void goListUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UsersDto usersDto = userService.getAllUsersDto();

        request.setAttribute("usersDto", usersDto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/users.jsp");
        dispatcher.forward(request, response);
    }

    private void goRegisterUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    private void goHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");

        dispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }



}
