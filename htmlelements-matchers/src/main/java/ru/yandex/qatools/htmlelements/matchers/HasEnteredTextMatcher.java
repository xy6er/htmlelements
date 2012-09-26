package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * @author Alexander Tolmachev starlight@yandex-team.ru
 *         Date: 26.09.12
 */
public class HasEnteredTextMatcher extends TypeSafeMatcher<TextInput> {
    private Matcher<String> matcher;

    public HasEnteredTextMatcher(Matcher<String> matcher) {
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(TextInput textInput) {
        return matcher.matches(textInput.getEnteredText());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("text input has entered text ");
    }

    @Override
    protected void describeMismatchSafely(TextInput textInput, Description mismatchDescription) {
        mismatchDescription.appendText(String.format("text input %s has not entered text ", textInput));
    }

    @Factory
    public static Matcher<TextInput> hasEnteredText(Matcher<String> textMatcher) {
        return new HasEnteredTextMatcher(textMatcher);
    }
}