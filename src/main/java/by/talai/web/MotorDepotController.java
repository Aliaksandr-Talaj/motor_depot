package by.talai.web;

import by.talai.data.dao.AddressDao;
import by.talai.data.dao.impl.AddressDaoImpl;
import by.talai.data.exception.ConnectionPoolException;
import by.talai.data.exception.DaoException;
import by.talai.model.*;
import by.talai.model.personnel.User;
import by.talai.model.stock.Automobile;
import by.talai.model.stock.AutomobileType;
import by.talai.model.stock.LoadingType;
import by.talai.service.*;
import by.talai.service.dto.DriverDto;
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
import java.sql.Date;
import java.util.ArrayList;
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
        request.getSession().setAttribute("usrId", 4);

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
                    case "/user/dispatcher/request_generated":
                        if ("dispatcher".equals(role))
                            saveRequestGoRequests(request, response);
                        break;
                    case "/user/dispatcher/save_delivery":
                        if ("dispatcher".equals(role))
                            saveDeliveriesGoRequestForm8(request, response);
                        break;
                    case "/user/dispatcher/save_cargo":
                        if ("dispatcher".equals(role))
                            saveDeliveryGoRequestForm7(request, response);
                        break;
                    case "/user/dispatcher/add_cargo":
                        if ("dispatcher".equals(role))
                            addCargoGoRequestForm6(request, response);
                        break;
                    case "/user/dispatcher/dates_chosen":
                        if ("dispatcher".equals(role))
                            addDatesAndGoRequestForm5(request, response);
                        break;
                    case "/user/dispatcher/request-form4":
                        if ("dispatcher".equals(role))
                            addAddressAndGoRequestForm4(request, response);
                        break;
                    case "/user/dispatcher/create_destination_address4req":
                        if ("dispatcher".equals(role))
                            addAddress4Req2(request, response);
                        break;
                    case "/user/dispatcher/request-form3":
                        if ("dispatcher".equals(role))
                            addAddressAndGoRequestForm3(request, response);
                        break;
                    case "/user/dispatcher/create_loading_address4req":
                        if ("dispatcher".equals(role))
                            addAddress4Req(request, response);
                        break;
                    case "/user/dispatcher/address_chosen2":
                        if ("dispatcher".equals(role))
                            selectAddressAndGoRequestForm4(request, response);
                        break;
                    case "/user/dispatcher/address_chosen":
                        if ("dispatcher".equals(role))
                            selectAddressAndGoRequestForm3(request, response);
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
                            goListCharterers(request, response);
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

    private void saveRequestGoRequests(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
////////////////////////////////////////////
        int typeId = Integer.parseInt(request.getParameter("type"));
        int loadingTypeId = Integer.parseInt(request.getParameter("loadingType"));
        Request generatingRequest = (Request) request.getSession().getAttribute("generatingRequest");
        if (loadingTypeId != 0) {
            LoadingType loadingType = new LoadingType();
            loadingType.setId(loadingTypeId);
            generatingRequest.setRequiredLoadingType(loadingType);
        }
        if (typeId != 0) {
            AutomobileType automobileType = new AutomobileType();
            automobileType.setId(typeId);
            generatingRequest.setRequiredAutomobileType(automobileType);
        }
        int userid = (int) request.getSession().getAttribute("usrId");
        RequestService requestService = new RequestServiceImpl();
        requestService.addNewRequest(generatingRequest, userid);
        request.getSession().removeAttribute("generatingRequest");

        goListRequests(request, response);
    }

    private void saveDeliveriesGoRequestForm8(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean addMore = Boolean.parseBoolean(request.getParameter("addMoreDeliveries"));
        request.getSession().removeAttribute("generatingDelivery");
        RequestDispatcher dispatcher;
        if (addMore) {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form2.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form8.jsp");
        }
        dispatcher.forward(request, response);

    }


    private void saveDeliveryGoRequestForm7(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean addMore = Boolean.parseBoolean(request.getParameter("addMoreCargos"));

        if (addMore) {


            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form5.jsp");
            dispatcher.forward(request, response);
        } else {
            Request generatingRequest = (Request) request.getSession().getAttribute("generatingRequest");
            Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
            //adding addresses, used in the delivery, into the charterer's list
            AddressService addressService = new AddressServiceImpl();
            Address address1 = addressService.addAddressIfNew(delivery.getDestination());
            Address address2 = addressService.addAddressIfNew(delivery.getLoadingPlace());
            chartererService.addNewAddressToCharterer(address1, generatingRequest.getCharterer());
            chartererService.addNewAddressToCharterer(address2, generatingRequest.getCharterer());

            //saving delivery into DB
            ///////////////////////////////////////////////////
            delivery.setRequest(generatingRequest);
            ///////////////////////////////////////////////
            DeliveryService deliveryService = new DeliveryServiceImpl();
            delivery = deliveryService.addDelivery(delivery);

            //adding delivery into the generating request
            List<Delivery> deliveryList = generatingRequest.getDeliveryList();
            if (deliveryList == null) {
                deliveryList = new ArrayList<>();
            }
            deliveryList.add(delivery);
            generatingRequest.setDeliveryList(deliveryList);
            request.getSession().setAttribute("generatingRequest", generatingRequest);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form7.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void addCargoGoRequestForm6(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //parsing of the cargo's name
        String cargoName = request.getParameter("cargoName");
        //parsing of unit's parameters
        String unitType = request.getParameter("unitType");
        int unitLength = Integer.parseInt(request.getParameter("unitLength"));
        int unitWidth = Integer.parseInt(request.getParameter("unitWidth"));
        double unitHeight = Double.parseDouble(request.getParameter("unitHeight"));
        double unitWeight = Double.parseDouble(request.getParameter("unitWeight"));
        double quantity = Double.parseDouble(request.getParameter("quantity"));

        // getting of cargo and delivery from the session scope
        Cargo cargo = new Cargo();
        Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");

        UnitService unitService = new UnitServiceImpl();
        //adding new unit into DB
        int unitId = unitService.addNewUnit(unitType, unitLength, unitWidth, unitHeight, unitWeight);
        Unit unit = unitService.findUnit(unitId);
        //setting it into the cargo
        List<Cargo> cargos = delivery.getCargoList();
        cargo.setName(cargoName);
        cargo.setUnit(unit);
        cargo.setQuantity(quantity);
        if (cargos == null) {
            cargos = new ArrayList<>();
        }
        cargos.add(cargo);
        delivery.setCargoList(cargos);

        request.getSession().setAttribute("generatingDelivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form6.jsp");
        dispatcher.forward(request, response);
    }


    private void addDatesAndGoRequestForm5(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Date loadingDate = Date.valueOf(request.getParameter("loadingDate"));
        Date unloadingDate = Date.valueOf(request.getParameter("unloadingDate"));
        Request generatingRequest = (Request) request.getSession().getAttribute("generatingRequest");
        RequestService requestService = new RequestServiceImpl();
        boolean datesAreValid = requestService.validateDates(loadingDate, unloadingDate);
        if (datesAreValid) {
            Date now = new Date(new java.util.Date().getTime());
            generatingRequest.setFillingDate(now);

            Delivery generatingDelivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
            generatingDelivery.setLoadingDate(loadingDate);
            generatingDelivery.setTerm(unloadingDate);
            request.getSession().setAttribute("generatingDelivery", generatingDelivery);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form5.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("invalidInput", Boolean.TRUE);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form4.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void addAddress4Req2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("for_request", 4);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/address-form.jsp");
        dispatcher.forward(request, response);
    }

    private void selectAddressAndGoRequestForm4(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int addressId = Integer.parseInt(request.getParameter("destinationAddressId"));
        AddressDao addressDao = new AddressDaoImpl();
        Address address = addressDao.getAddress(addressId);
        Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
        delivery.setDestination(address);
        request.getSession().setAttribute("generatingDelivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form4.jsp");
        dispatcher.forward(request, response);
    }

    private void addAddressAndGoRequestForm4(HttpServletRequest request, HttpServletResponse response)
            throws ConnectionPoolException, DaoException, ServletException, IOException {

        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        AddressService addressService = new AddressServiceImpl();
        int addressId = addressService.addNewAddress(country, region, locality, street,
                building, apartment);
        Address address = new Address(addressId, country, region, locality, street,
                building, apartment);
        Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
        delivery.setDestination(address);
        request.getSession().setAttribute("generatingDelivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form4.jsp");
        dispatcher.forward(request, response);
    }

    private void addAddress4Req(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("for_request", 3);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/address-form.jsp");
        dispatcher.forward(request, response);

    }

    private void addAddressAndGoRequestForm3(HttpServletRequest request, HttpServletResponse response)
            throws ConnectionPoolException, DaoException, ServletException, IOException {

        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        AddressService addressService = new AddressServiceImpl();
        int addressId = addressService.addNewAddress(country, region, locality, street,
                building, apartment);
        Address address = new Address(addressId, country, region, locality, street,
                building, apartment);
        Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
        delivery.setLoadingPlace(address);
        request.getSession().setAttribute("generatingDelivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form3.jsp");
        dispatcher.forward(request, response);
    }

    private void selectAddressAndGoRequestForm3(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int addressId = Integer.parseInt(request.getParameter("loadingAddressId"));
        AddressDao addressDao = new AddressDaoImpl();
        Address address = addressDao.getAddress(addressId);
        Delivery delivery = (Delivery) request.getSession().getAttribute("generatingDelivery");
        delivery.setLoadingPlace(address);
        request.getSession().setAttribute("generatingDelivery", delivery);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/req-form3.jsp");
        dispatcher.forward(request, response);
    }

    private void addChartererAndGoRequestForm2(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int chartererId = createCharterer(request);
        goRequestForm2(request, response, chartererId);
    }

    private int createCharterer(HttpServletRequest request) throws DaoException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String country = request.getParameter("country");
        String region = request.getParameter("region");
        String locality = request.getParameter("locality");
        String street = request.getParameter("street");
        String building = request.getParameter("building");
        String apartment = request.getParameter("apartment");
        return chartererService.addCharterer(name, surname, country, region, locality, street,
                building, apartment);
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
        createCharterer(request);

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
        request.getSession().removeAttribute("generatingRequest");
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

    private void goListCharterers(HttpServletRequest request, HttpServletResponse response)
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
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
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
