package ru.yandex.qatools.htmlelements.matchers;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static ru.yandex.qatools.htmlelements.matchers.decorators.MatcherDecoratorsBuilder.should;
import static ru.yandex.qatools.htmlelements.matchers.decorators.TimeoutWaiter.timeoutHasExpired;

/**
 * Created with IntelliJ IDEA.
 * User: lanwen
 * Date: 10.01.13
 * Time: 19:39
 */
// TODO: Refacor this test
@RunWith(MockitoJUnitRunner.class)
public class TimeoutWaiterMatcherDecoratorTest {
    @Mock
    private Matcher<Object> matcher;

    @Test
    public void decShouldTryWhileTimerGoesOut() throws Exception {
        Object ANY_OBJECT = "";

        when(matcher.matches(any())).thenReturn(false);

        Boolean result = should(matcher).
                whileWaitingUntil(timeoutHasExpired(SECONDS.toMillis(2)).withPollingInterval(MILLISECONDS.toMillis(250))).
                matches(ANY_OBJECT);

        // 8 for check + return = 9
        verify(matcher, times(9)).matches(ANY_OBJECT);

        assertThat("Miracle! False now is true!", result, is(false));
    }
//
//    @Test
//    public void orUntilAddsAdditionalCondition() {
//        when(matcher.matches(any())).thenReturn(false);
//
//        Boolean result = withWaitFor(matcher).andWhile(twoSecondsNotExpired).matches(ANY_OBJECT);
//
//        verify(matcher, times(5)).matches(ANY_OBJECT);
//        assertThat("Miracle! False now is true!", result, is(false));
//    }
//
//    @Test
//    public void withTimeoutOverridesDefaultTimeout() {
//        when(matcher.matches(any())).thenReturn(false);
//
//        Boolean result = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1)).matches(ANY_OBJECT);
//        verify(matcher, times(3)).matches(ANY_OBJECT);
//        assertThat("Miracle! False now is true!", result, is(false));
//    }
//
//    @Test
//    public void withTimeoutShouldNotOverrideCondition() {
//        when(matcher.matches(any())).thenReturn(false);
//
//        Boolean result = withWaitFor(matcher).andWhile(twoSecondsNotExpired).withTimeout(SECONDS.toMillis(5)).matches(ANY_OBJECT);
//        verify(matcher, times(5)).matches(ANY_OBJECT);
//        assertThat("Miracle! False now is true!", result, is(false));
//    }
//
//    @Test
//    public void timeoutConditionShouldNotStartImmediately() throws InterruptedException {
//        when(matcher.matches(any())).thenReturn(false);
//
//        Matcher<? super Object> withWaitForMatcher = withWaitFor(matcher).withTimeout(SECONDS.toMillis(1));
//        Thread.sleep(SECONDS.toMillis(2));
//        Boolean result = withWaitForMatcher.matches(ANY_OBJECT);
//        verify(matcher, times(3)).matches(ANY_OBJECT);
//        assertThat("Miracle! False now is true!", result, is(false));
//    }
//
//    @Test
//    public void allConditionsShouldBeChecked() {
//        when(matcher.matches(any())).thenReturn(false);
//
//        TimeoutCondition oneSecondNotExpired = new TimeoutCondition(SECONDS.toMillis(1));
//
//        Boolean result = withWaitFor(matcher).andWhile(oneSecondNotExpired).andWhile(twoSecondsNotExpired).matches(ANY_OBJECT);
//        verify(matcher, times(3)).matches(ANY_OBJECT);
//        assertThat("Miracle! False now is true!", result, is(false));
//    }
//
//
//    @Test
//    public void descriptionShouldContainAllConditionDescriptions() throws Exception {
//        long timeOut = SECONDS.toMillis(15);
//
//        String descriptionOne = "When the hell will come";
//        String descriptionTwo = "When developers will program without bugs";
//        String descriptionOfWaiter = "While waiting [<" + timeOut + "L>] millis";
//
//        String or = " or ";
//
//        Description description = new StringDescription();
//
//        withWaitFor(is(false))
//                .andWhile(describedCondition(descriptionOne))
//                .andWhile(describedCondition(descriptionTwo))
//                .withTimeout(timeOut).describeTo(description);
//
//        assertThat(description.toString(),
//                startsWith(on(or).join(descriptionOfWaiter, descriptionOne, descriptionTwo)));
//    }
//
//    private Condition describedCondition(final String stringDescription) {
//        return new Condition() {
//            @Override
//            public boolean isTrue() {
//                return false;
//            }
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText(stringDescription);
//            }
//        };
//    }
}
