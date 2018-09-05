package com.kuriata.validators;

import java.util.List;
import java.util.regex.Pattern;

public class Validator implements IValidator {
    private static final Pattern LOGIN_PATTERN =
            Pattern.compile("^([A-Z0-9_.-]+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern USER_PASSWORD_PATTERN =
            Pattern.compile("^([A-Za-z0-9_\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()]{4,})$");
    private static final Pattern USER_EMAIL_PATTERN =
            Pattern.compile("^([a-z0-9_.-]+)@([a-z0-9_.-]+).([a-z.]{2,6})$");
    private static final Pattern USER_FIRST_AND_LAST_NAME_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇєґіїЭ][a-zа-яєяґіїЭ]+)$");
    private static final Pattern USER_PHONE_PATTERN =
            Pattern.compile("^(\\+[0-9]{12})$");
    private static final Pattern BOOK_SHORT_TITLE_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇ][\\w0-9\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()\\sА-яЄЯҐІЇєґії]{10,99})$");
    private static final Pattern BOOK_FULL_TITLE_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇ][\\w0-9\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()\\sА-яЄЯҐІЇєґії]{10,299})$");
    private static final Pattern BOOK_DESCRIPTION_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇ][\\w0-9\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()\\sА-яЄЯҐІЇєґії]{10,499})$");
    private static final Pattern BOOK_KEY_WORDS_PATTERN =
            Pattern.compile("^([\\w0-9\\-\\+.#@!&$*\\sА-яЄЯҐІЇєґії]{10,100})$");
    private static final Pattern AUTHOR_FULL_NAME_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇ][\\w\\-()\\sА-яЄЯҐІЇєґії]{4,59})$");
    private static final Pattern AUTHOR_DESCRIPTION_PATTERN =
            Pattern.compile("^([A-ZА-ЯЄҐІЇ][\\w\\-()\\sА-яЄЯҐІЇєґії]{2,29})$");
    private static final Pattern SHELF_NAME_PATTERN =
            Pattern.compile("^[\\w0-9\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()\\sА-яєґії]{3,30}$");
    private static final Pattern SHELF_ADDRESS_PATTERN =
            Pattern.compile("^[A-Z]{1,2}[0-9]{1,4}$");
    private static final Pattern SHELF_DESCRIPTION_PATTERN =
            Pattern.compile("^[\\w0-9\\\\\\/\\-\\+.,;:'\"#@!&$*\\<\\>\\[\\]()\\sА-яєґії]{2,100}$", Pattern.CASE_INSENSITIVE);

    @Override
    public boolean isUserLoginValid(String userLogin) {
        return LOGIN_PATTERN.matcher(userLogin).matches();
    }

    @Override
    public boolean isUserPasswordValid(String userPassword) {
        return USER_PASSWORD_PATTERN.matcher(userPassword).matches();
    }

    @Override
    public boolean isUserPasswordsMached(String userPassword, String userPasswordConfirmation) {
        return userPassword.equals(userPasswordConfirmation);
    }

    @Override
    public boolean isUserEmailValid(String email) {
        return USER_EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean isUserFirstNameValid(String userFirsName) {
        return USER_FIRST_AND_LAST_NAME_PATTERN.matcher(userFirsName).matches();
    }

    @Override
    public boolean isUserLastNameValid(String userLastName) {
        return USER_FIRST_AND_LAST_NAME_PATTERN.matcher(userLastName).matches();
    }

    @Override
    public boolean isUserPhoneValid(String userPhone) {
        return USER_PHONE_PATTERN.matcher(userPhone).matches();
    }

    @Override
    public boolean isBookShortTitleValid(String bookShortTitle) {
        return BOOK_SHORT_TITLE_PATTERN.matcher(bookShortTitle).matches();
    }

    @Override
    public boolean isBookFullTitleValid(String bookFullTitle) {
        return BOOK_FULL_TITLE_PATTERN.matcher(bookFullTitle).matches();
    }

    @Override
    public boolean isBookDescriptionValid(String bookDescription) {
        return BOOK_DESCRIPTION_PATTERN.matcher(bookDescription).matches();
    }

    @Override
    public boolean isKeyworsValid(String bookKeyWords) {
        return BOOK_KEY_WORDS_PATTERN.matcher(bookKeyWords).matches();
    }

    @Override
    public boolean isBookAuthorsValid(List<Integer> authorsIdList) {
        return authorsIdList.size() > 0;
    }

    @Override
    public boolean isBookQuantityValid(int bookQuantity) {
        return bookQuantity >= 0;
    }

    @Override
    public boolean isAuthorFullNameValid(String authorFullName) {
        return AUTHOR_FULL_NAME_PATTERN.matcher(authorFullName).matches();
    }

    @Override
    public boolean isAuthorDetailsValid(String authorDetails) {
        return AUTHOR_DESCRIPTION_PATTERN.matcher(authorDetails).matches();
    }

    @Override
    public boolean isShelfeNameValid(String shelfName) {
        return SHELF_NAME_PATTERN.matcher(shelfName).matches();
    }

    @Override
    public boolean isShelfAddressValid(String shelfAddress) {
        return SHELF_ADDRESS_PATTERN.matcher(shelfAddress).matches();
    }

    @Override
    public boolean isShelfDescriptionValid(String shelfDescription) {
        return SHELF_DESCRIPTION_PATTERN.matcher(shelfDescription).matches();
    }
}
