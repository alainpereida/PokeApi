package com.example.pokeapi;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.pokeapi.Activitys.LoginActivity;
import com.example.pokeapi.Controllers.LoginController;
import com.example.pokeapi.Utils.ConsultaPreferences;
import com.example.pokeapi.Models.User;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class) @LargeTest public class LoginTest {

    private LoginController loginController;
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivitytestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testLogin() {
        loginController = new LoginController(mLoginActivityActivitytestRule.getActivity().getApplicationContext());
        if (loginController.exitUser()) {
            return;
        }

        onView(withId(R.id.getEmailAddress))
                .perform(typeText("alain.pereida@gmail.com"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.getPassword))
                .perform(typeText("password"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.button_login))
                .perform(click());
    }
}
