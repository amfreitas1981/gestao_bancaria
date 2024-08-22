package com.banking.account.transact.infra.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TreatErrorsTest {

    private final TreatErrors treatErrors = new TreatErrors();

    @Test
    void testHandleNegativeBalanceException() {
        ResponseEntity<?> response = treatErrors.treatErrorNegativeBalance();
        assertEquals(412, response.getStatusCodeValue()); // 412 Precondition Failed
    }

    @Test
    void testHandleAccountNotFoundException() {
        ResponseEntity<?> response = treatErrors.treatErrorAccountNotFound();
        assertEquals(404, response.getStatusCodeValue()); // 404 Not Found
    }

    @Test
    void testHandleEntityNotFoundException() {
        ResponseEntity<?> response = treatErrors.treatError404();
        assertEquals(404, response.getStatusCodeValue()); // 404 Not Found
    }

    @Test
    void testHandleMethodArgumentNotValidException() {
        // Prepare mock
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        FieldError fieldError = new FieldError("object", "field", "error message");
        when(ex.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<?> response = treatErrors.treatError400(ex);
        assertEquals(400, response.getStatusCodeValue()); // 400 Bad Request
        assertTrue(response.getBody() instanceof List);
        assertEquals(1, ((List<?>) response.getBody()).size());
        assertTrue(((List<?>) response.getBody()).get(0) instanceof TreatErrors.DataErrorValidation);
    }

    @Test
    void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("message");
        ResponseEntity<?> response = treatErrors.treatError400(ex);
        assertEquals(400, response.getStatusCodeValue()); // 400 Bad Request
        assertEquals("message", response.getBody());
    }

    @Test
    void testHandleBadCredentialsException() {
        ResponseEntity<?> response = treatErrors.treatErrorBadCredentials();
        assertEquals(401, response.getStatusCodeValue()); // 401 Unauthorized
        assertEquals("Invalid credencials", response.getBody());
    }

    @Test
    void testHandleAuthenticationException() {
        ResponseEntity<?> response = treatErrors.treatErrorAuthentication();
        assertEquals(401, response.getStatusCodeValue()); // 401 Unauthorized
        assertEquals("Authentication failure", response.getBody());
    }

    @Test
    void testHandleAccessDeniedException() {
        ResponseEntity<?> response = treatErrors.treatErrorAccessDenied();
        assertEquals(403, response.getStatusCodeValue()); // 403 Forbidden
        assertEquals("Access denied", response.getBody());
    }

    @Test
    void testHandleGenericException() {
        Exception ex = new Exception("Generic error");
        ResponseEntity<?> response = treatErrors.treatError500(ex);
        assertEquals(500, response.getStatusCodeValue()); // 500 Internal Server Error
        assertEquals("Error: Generic error", response.getBody());
    }
}
