package com.skripsi.andra;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class lupapassTest {
    private lupapass lupapass;
    private FirebaseAuth firebaseAuth;

    @Before
    public void setUp() {
        firebaseAuth = mock(FirebaseAuth.class);
        lupapass = new lupapass();
        lupapass.firebaseAuth = firebaseAuth;
    }

    /**
     * Should send email to user when the email is valid
     */
    @Test
    public void lupapasswordWhenEmailIsValidThenSendEmailToUser() {
        lupapass.email = mock(EditText.class);
        Mockito.when(lupapass.email.getText().toString()).thenReturn("test@gmail.com");
        new lupapass().lupapassword();
        verify(firebaseAuth).sendPasswordResetEmail("test@gmail.com");
    }
}