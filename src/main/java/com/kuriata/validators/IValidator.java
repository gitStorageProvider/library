package com.kuriata.validators;

import java.util.List;

public interface IValidator {
    boolean isUserLoginValid(String userLogin);
    boolean isUserPasswordValid(String userPassword);
    boolean isUserPasswordsMached(String userPassword, String userPasswordConfirmation);
    boolean isUserEmailValid(String email);
    boolean isUserFirstNameValid(String userFirsName);
    boolean isUserLastNameValid(String userLastName);
    boolean isUserPhoneValid(String userPhone);

    boolean isBookShortTitleValid(String bookShortTitle);
    boolean isBookFullTitleValid(String bookFullTitle);
    boolean isBookDescriptionValid(String bookDescription);
    boolean isKeyworsValid(String bookKeyWords);
    boolean isBookAuthorsValid(List<Integer> authorsIdList);
    boolean isBookQuantityValid(int bookQuantity);

    boolean isAuthorFullNameValid(String authorFullName);
    boolean isAuthorDetailsValid(String authorDetails);

    boolean isShelfeNameValid(String shelfName);
    boolean isShelfAddressValid(String shelfAddress);
    boolean isShelfDescriptionValid(String shelfDescription);

    }
