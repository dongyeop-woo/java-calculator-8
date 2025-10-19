package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    
    @Test
    void 빈_문자열_입력시_0_반환() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 숫자_하나만_입력() {
        assertSimpleTest(() -> {
            run("5");
            assertThat(output()).contains("결과 : 5");
        });
    }

    @Test
    void 쉼표_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 콜론_구분자_사용() {
        assertSimpleTest(() -> {
            run("1:2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 쉼표와_콜론_혼합_구분자_사용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 커스텀_구분자_세미콜론으로_여러_숫자_계산() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_별표_사용() {
        assertSimpleTest(() -> {
            run("//*\\n1*2*3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자와_기본_구분자_혼합() {
        assertSimpleTest(() -> {
            run("//;\\n1;2,3:4");
            assertThat(output()).contains("결과 : 10");
        });
    }

    @Test
    void 큰_숫자_계산() {
        assertSimpleTest(() -> {
            run("100,200,300");
            assertThat(output()).contains("결과 : 600");
        });
    }

    @Test
    void 많은_숫자_계산() {
        assertSimpleTest(() -> {
            run("1,2,3,4,5,6,7,8,9,10");
            assertThat(output()).contains("결과 : 55");
        });
    }

    @Test
    void 예외_테스트_음수_입력() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_음수_단독_입력() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-5"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_숫자가_아닌_값() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_특수문자_입력() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("1,@,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_잘못된_커스텀_구분자_형식() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("//;"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
