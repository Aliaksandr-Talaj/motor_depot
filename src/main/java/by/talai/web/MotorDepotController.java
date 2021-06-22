package by.talai.web;

import by.talai.data.exception.DaoException;
import by.talai.model.*;
import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;
import by.talai.service.*;
import by.talai.service.dto.DriverDto;
import by.talai.service.dto.RequestDto;
import by.talai.service.dto.UsersDto;
import by.talai.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("local") != null) {
            request.getSession(true).setAttribute("local", request.getParameter("local"));
        }
        request.getSession(true).setAttribute("role", "dispatcher");
        String role = (String) request.getSession(true).getAttribute("role");

        String action = request.getServletPath();


        if (action != null) {
            try {
//all can do
                switch (action) {
                    case "/login":
                        login(request, response);
                        break;
                    case "/logout":
                        logout(request, response);
                        break;
                    case "/auth":
                        goAuthorise(request, response);
                        break;
// admins can do
                    case "/admin/registration":
                        if ("admin".equals(role))
                            goRegisterUser(request, response);
                        break;
                    case "/admin/users":
                        goListUsers(request, response);
                        break;
                    case "/admin/add_user":
                        if ("admin".equals(role))
                            goAddUser(request, response);
                        break;
                    case "/admin/users/c_status":
                        if ("admin".equals(role))
                            changeUserStatus(request, response);
                        break;
//dispatchers can do

//                    case "/user/dispatcher/address_chosen2":
//                        if ("dispatcher".equals(role))
//                            addChartererAndGoRequestForm3(request, response);
//                        break;
                    case "/user/dispatcher/address_chosen":
                        if ("dispatcher".equals(role))
                            addChartererAndGoRequestForm3(request, response);
                        break;
                    case "/user/dispatcher/request-form2":
                        if ("dispatcher".equals(role))
                            addChartererAndGoRequestForm2(request, response);
                        break;
                    case "/user/dispatcher/charterer_chosen":
                        if ("dispatcher".equals(role))
                            setChartererAndGoRequestForm2(request, response);
                        break;
                    case "/admin/add_auto":
                        if ("dispatcher".equals(role))
                            addNewAuto(request, response);
                        break;
                    case "/user/dispatcher/create_charterer4req":
                        if ("dispatcher".equals(role))
                            goAddCharterer(request, response);
                        break;
                    case "/user/dispatcher/v_attachment":
                        if ("dispatcher".equals(role))
                            goViewAttachment(request, response);
                        break;
                    case "/user/dispatcher/autos":
                        if ("dispatcher".equals(role))
                            goListAutomobiles(request, response);
                        break;
                    case "/user/dispatcher/auto-form":
                        if ("dispatcher".equals(role))
                            goAutomobileForm(request, response);
                        break;
                    case "/user/dispatcher/charterer-form":
                        if ("dispatcher".equals(role))
                            goChartererForm(request, response);
                        break;
                    case "/user/dispatcher/add-charterer":
                        if ("dispatcher".equals(role))
                            addCharterer(request, response);
                        break;
                    case "/user/dispatcher/charterers":
                        if ("dispatcher".equals(role))
                            goCharterers(request, response);
                        break;
                    case "/user/dispatcher/charterer":
                        if ("dispatcher".equals(role))
                            goGetCharterer(request, response);
                        break;
                    case "/user/dispatcher/driver-form":
                        if ("dispatcher".equals(role))
                            goDriverForm(request, response);
                        break;
                    case "/user/dispatcher/drivers":
                        if ("dispatcher".equals(role))
                            goListDrivers(request, response);
                        break;
                    case "/user/dispatcher/request-form":
                        if ("dispatcher".equals(role))
                            goRequestForm(request, response);
                        break;
                    case "/user/dispatcher/maintenances":
                        if ("dispatcher".equals(role))
                            goListMaintenances(request, response);
                        break;
                    //users can do
                    case "/user/requests":
                        if ("dispatcher".equals(role)
                                || "driver".equals(role))
                            goListRequests(request, response);
                        break;
                    case "/user/request":
                        if ("dispatcher".equals(role)
                                || "driver".equals(role))
                            goGetRequest(request, response);
                        break;
                    case "/user/rides":
                        if ("dispatcher".equals(role)
                                || "driver".equals(role))
                            goListRides(request, response);
                        break;
                    case "/user/cargo":
                        if ("dispatcher".equals(role)
                                || "driver".equals(role))
                            goGetCargo(request, response);
                        break;
                    case "/user/delivery":
                        if ("dispatcher".equals(role)
                                || "driver".equals(role))
                            goGetDelivery(request, response);
                        break;
                    //drivers can do
                    case "/user/driver/auto":
                        if ("driver".equals(role))
                            goAutomobile(request, response);
                        break;
                    case "/user/driver/repair-request":
                        if ("driver".equals(role))
                            goRequestRepair(request, response);
                        break;
                    default:
                        goHome(request, response);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("Exception in doGet() method", ex);
                throw new ServletException(ex);

            }
        }

    }

    private void addChartererAndGoRequestForm3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form3.jsp");
        dispatcher.forward(request, response);
    }

    private void addChartererAndGoRequestForm2(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        int chartererId = chartererService.addCharterer(name, surname, country, region, locality, street,
                building, apartment);
        goRequestForm2(request, response, chartererId);
    }

    private void goRequestForm2(HttpServletRequest request, HttpServletResponse response, int chartererId) throws Exception {
        Request generatingRequest = (Request) request.getSession().getAttribute("generatingRequest");
        ChartererService chartererService = new ChartererServiceImpl();
        Charterer charterer = chartererService.getCharterer(chartererId);
        generatingRequest.setCharterer(charterer);
        request.getSession().setAttribute("generatingRequest", generatingRequest);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form2.jsp");
        dispatcher.forward(request, response);
    }

    private void addNewAuto(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String fuel = request.getParameter("fuel");
        String carrying = request.getParameter("carrying");
        String type = request.getParameter("type");
        String equipment1 = request.getParameter("equipment1");
        String equipment2 = request.getParameter("equipment2");
        String equipment3 = request.getParameter("equipment3");
        String top = request.getParameter("top");
        String rear = request.getParameter("rear");
        String side = request.getParameter("side");
        String platformLength = request.getParameter("p_length");
        String platformWidth = request.getParameter("p_width");
        String heightLimit = request.getParameter("h_limit");
        String volumeLimit = request.getParameter("v_limit");
//TODO check
        automobileService.addNewAutomobile(id, brand, model, fuel, carrying, type, equipment1, equipment2, equipment3,
                top, rear, side, platformLength, platformWidth, heightLimit, volumeLimit);
        response.sendRedirect("/motor_depot/user/dispatcher/autos");
    }

    private void setChartererAndGoRequestForm2(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            int chartererId = Integer.parseInt(request.getParameter("chartererId"));
            goRequestForm2(request, response, chartererId);
        } catch (NumberFormatException e) {
            goRequestForm(request, response);
        }
    }

    private void goAddCharterer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("for_request", 1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/charterer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goViewAttachment(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");
        AutomobileService automobileService = new AutomobileServiceImpl();
        Automobile automobile = automobileService.findAutomobileById(id);
        request.setAttribute("automobile", automobile);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/auto-attachments.jsp");
        dispatcher.forward(request, response);
    }

    private void goAuthorise(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isValid = userService.validate(login, password);

        RequestDispatcher dispatcher;
        if (isValid) {
            User user = userService.findUser(login);
            Role role = user.getRole();
            System.out.println(role);
            System.out.println(role.getName());
            HttpSession session = request.getSession(true);
            session.setAttribute("role", role.getName());
            session.setAttribute("usrId", user.getId());
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        }

        dispatcher.forward(request, response);
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

    private void addCharterer(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        int chartererId = chartererService.addCharterer(name, surname, country, region, locality, street,
                building, apartment);

//        int goToReq2Form = Integer.parseInt(request.getParameter("goToReq2Form"));
//        if (goToReq2Form == 1) {
//            goRequestForm2(request, response, chartererId);
//        }
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

    private void goRequestForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Charterer> charterers = chartererService.getCharterers();
        request.setAttribute("new_request", 1);
        request.setAttribute("charterers", charterers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form1.jsp");
        dispatcher.forward(request, response);
    }

    private void goListDrivers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<DriverDto> drivers = userService.getAllDriversDtoList();
        request.setAttribute("drivers", drivers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/drivers.jsp");
        dispatcher.forward(request, response);
    }

    private void goDriverForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/driver-form.jsp");
        dispatcher.forward(request, response);
    }

    private void goCharterers(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<Charterer> charterers = chartererService.getCharterers();

        request.setAttribute("charterers", charterers);

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
        List<Automobile> automobiles = automobileService.findAllAutomobiles();

        request.setAttribute("automobiles", automobiles);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autos.jsp");
        dispatcher.forward(request, response);
    }

    private void goListRequests(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestService requestService = new RequestServiceImpl();
        List<RequestDto> requestDtoList = requestService.getRequestDtoList();
        request.setAttribute("requestsDto", requestDtoList);
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
            throws ServletException, IOException {
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
