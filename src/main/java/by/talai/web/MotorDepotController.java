package by.talai.web;

import by.talai.data.exception.DaoException;
import by.talai.model.Cargo;
import by.talai.model.Charterer;
import by.talai.model.Delivery;
import by.talai.model.Ride;
import by.talai.service.*;
import by.talai.service.dto.AutomobilesDto;
import by.talai.service.dto.UsersDto;
import by.talai.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MotorDepotController extends HttpServlet {

    private final AutomobileService automobileService = new AutomobileServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final ChartererService chartererService = new ChartererServiceImpl();


    private final Logger logger = LoggerFactory.getLogger(MotorDepotController.class);

    public MotorDepotController() throws Exception {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (request.getParameter("local") != null) {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
        }


        request.getSession(true).setAttribute("role", "dispatcher");


        String action = request.getServletPath();
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
                    case "/admin/add_user":
                        goAddUser(request, response);
                        break;
                    case "/admin/users/c_status":
                        changeUserStatus(request, response);
                        break;
                    case "/user/requests":
                        goListRequests(request, response);
                        break;
                    case "/user/request":
                        goGetRequest(request, response);
                        break;
                    case "/user/rides":
                        goListRides(request, response);
                        break;
                    case "/user/cargo":
                        goGetCargo(request, response);
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
                    case "/user/dispatcher/add-charterer":
                        addCharterer(request, response);
                        break;
                    case "/user/dispatcher/charterers":
                        goCharterers(request, response);
                        break;
                    case "/user/dispatcher/charterer":
                        goGetCharterer(request, response);
                        break;
                    case "/user/delivery":
                        goGetDelivery(request, response);
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
                logger.error("Exception in doGet() method", ex);
                throw new ServletException(ex);

            }
        }

    }

    private void goGetCargo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        CargoService cargoService = new CargoServiceImpl();
        Cargo cargo = cargoService.getCargo(id);

        request.setAttribute("cargo", cargo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delivery.jsp");
        dispatcher.forward(request, response);
    }

    private void goGetRequest(HttpServletRequest request, HttpServletResponse response) {
        //TODO
    }

    private void goGetDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        DeliveryService deliveryService = new DeliveryServiceImpl();
        Delivery delivery = deliveryService.getDelivery(id);

        request.setAttribute("delivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/delivery.jsp");
        dispatcher.forward(request, response);
    }

    private void goGetCharterer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Charterer charterer = chartererService.getCharterer(id);

        request.setAttribute("charterer", charterer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterer.jsp");
        dispatcher.forward(request, response);
    }

    private void addCharterer(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        chartererService.addCharterer(name, surname, country, region, locality, street,
                building, apartment);
        response.sendRedirect("/motor_depot/user/dispatcher/charterers");
    }

    private void goAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        userService.addUser(name, surname, login, password, roleId, statusId);

        response.sendRedirect("/motor_depot/admin/users");
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

    private void goListRides(HttpServletRequest request, HttpServletResponse response) throws Exception {

        RideService rideService = new RideServiceImpl();
        List<Ride> rides = rideService.getRides();
                request.setAttribute("rides", rides);

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

    private void goCharterers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterers.jsp");
        dispatcher.forward(request, response);
    }

    private void goChartererForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goAutomobileForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/auto-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goListAutomobiles(HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        AutomobilesDto automobilesDto = automobileService.getAllAutomobilesDto();

        request.setAttribute("automobilesDto", automobilesDto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autos.jsp");
        dispatcher.forward(request, response);
    }

    private void goListRequests(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestService requestService = new RequestServiceImpl();
        request.setAttribute("requests", requestService.getAllRequests());
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
