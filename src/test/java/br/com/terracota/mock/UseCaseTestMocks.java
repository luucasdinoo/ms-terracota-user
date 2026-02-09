package br.com.terracota.mock;

import br.com.terracota.application.dto.input.*;
import br.com.terracota.application.dto.output.AuthLoginOutput;
import br.com.terracota.domain.enums.DocumentType;
import br.com.terracota.domain.enums.TypeUser;
import br.com.terracota.domain.model.*;
import br.com.terracota.domain.pagination.Pagination;
import br.com.terracota.domain.pagination.SearchFilter;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class UseCaseTestMocks {

    public static final String USER_ID = "878d345da8024a6183709a09d41cebc6";
    public static final String CUSTOMER_ID = "b4ee18695ae2447295628fe677110d48";
    public static final String CRAFTSMAN_ID = "dcf03a6a87854d59ab0279cfd6a20ad5";
    public static final String DOCUMENT_ID = "8b837d8b3d434f53b73d0bea758157da";
    public static final Date BIRTH_DATE_TEST = new Date();
    public static final Instant NOW_TEST = Instant.now();

    public static final User USER_TEST = User.with(
            USER_ID,
            "johndoe",
            "12345678",
            "John Doe",
            "johndoe123@gmail.com",
            "81999999999",
            Boolean.TRUE,
            TypeUser.USER,
            Set.of(Role.create("USER"))
    );

    public static final User USER_CUSTOMER_TEST = User.with(
            USER_ID,
            "johndoe",
            "12345678",
            "John Doe",
            "johndoe123@gmail.com",
            "81999999999",
            Boolean.TRUE,
            TypeUser.CUSTOMER,
            Set.of(Role.create("CUSTOMER"))
    );

    public static final Document DOCUMENT_TEST = Document.with(
            DOCUMENT_ID,
            "12345678900",
            DocumentType.CPF
    );

    public static final User USER_CRAFTSMAN_TEST = User.with(
            USER_ID,
            "johndoe",
            "12345678",
            "John Doe",
            "johndoe123@gmail.com",
            "81999999999",
            Boolean.TRUE,
            TypeUser.CUSTOMER,
            Set.of(Role.create("CRAFTSMAN"))
    );

    public static final Customer CUSTOMER_TEST = Customer.with(
            CUSTOMER_ID,
            USER_CUSTOMER_TEST,
            DOCUMENT_TEST,
            BIRTH_DATE_TEST,
            NOW_TEST,
            NOW_TEST
    );

    public static final Craftsman CRAFTSMAN_TEST = Craftsman.create(
            USER_CRAFTSMAN_TEST,
            Document.create("12345678900", DocumentType.CPF),
            BIRTH_DATE_TEST
    );

    public static final CreateUserInput CREATE_USER_INPUT = CreateUserInput.with(
            USER_TEST.getUsername(),
            USER_TEST.getPassword(),
            USER_TEST.getName(),
            USER_TEST.getEmail(),
            USER_TEST.getPhone(),
            USER_TEST.getUserType().getDescription(),
            USER_TEST.getRoles().stream().map(Role::getDescription).toList()
    );

    public static final UserInput USER_INPUT = UserInput.with(
            "johndoe",
            "12345678",
            "John Doe",
            "johndoe123@gmail.com",
            "81999999999"
    );

    public static final DocumentInput DOCUMENT_INPUT = DocumentInput.with(
            "12345678900",
            "CPF"
    );

    public static final CreateCustomerInput CREATE_CUSTOMER_INPUT = CreateCustomerInput.with(
            USER_INPUT,
            DOCUMENT_INPUT,
            BIRTH_DATE_TEST,
            TypeUser.CUSTOMER.getDescription()
    );

    public static final CreateCraftsmanInput CREATE_CRAFTSMAN_INPUT = CreateCraftsmanInput.with(
            USER_INPUT,
            DOCUMENT_INPUT,
            BIRTH_DATE_TEST,
            TypeUser.CRAFTSMAN.getDescription()
    );

    public static final SearchFilter SEARCH_FILTER_TEST = SearchFilter.with(
            "jon",
            "jon",
            "jon",
            "123",
            1,
            1,
            null,
            null
    );

    public static final List<User> USER_LIST = List.of(
            USER_TEST,
            USER_CUSTOMER_TEST,
            USER_CRAFTSMAN_TEST
    );

    public static final List<Customer> CUSTOMER_LIST = List.of(
            CUSTOMER_TEST,
            CUSTOMER_TEST,
            CUSTOMER_TEST
    );

    public static final List<Craftsman> CRAFTSMAN_LIST = List.of(
            CRAFTSMAN_TEST,
            CRAFTSMAN_TEST,
            CRAFTSMAN_TEST
    );

    public static final Pagination<User> SEARCH_USER_PAGINATION = new Pagination<>(
            0,
            SEARCH_FILTER_TEST.page(),
            SEARCH_FILTER_TEST.perPage(),
            USER_LIST
    );

    public static final Pagination<Customer> SEARCH_CUSTOMER_PAGINATION = new Pagination<>(
            0,
            SEARCH_FILTER_TEST.page(),
            SEARCH_FILTER_TEST.perPage(),
            CUSTOMER_LIST
    );

    public static final Pagination<Craftsman> SEARCH_CRAFTSMAN_PAGINATION = new Pagination<>(
            0,
            SEARCH_FILTER_TEST.page(),
            SEARCH_FILTER_TEST.perPage(),
            CRAFTSMAN_LIST
    );

    public static final UpdateCustomerInput UPDATE_CUSTOMER_INPUT = UpdateCustomerInput.with(
            USER_ID,
            "johndoe",
            "johndoe123@gmail.com",
            "John Doe",
            "81999999999"
    );

    public static final UpdateCraftsmanInput UPDATE_CRAFTSMAN_INPUT = UpdateCraftsmanInput.with(
            USER_ID,
            "johndoe",
            "johndoe123@gmail.com",
            "John Doe",
            "81999999999"
    );

    public static final AuthLoginInput AUTH_LOGIN_INPUT = AuthLoginInput.with(
            "johndoe",
            "12345678"
    );

    public static final AuthLoginOutput AUTH_LOGIN_OUTPUT = AuthLoginOutput.with(
            "johndoe",
            Boolean.TRUE,
            NOW_TEST,
            NOW_TEST,
            "accessToken"
    );
}
