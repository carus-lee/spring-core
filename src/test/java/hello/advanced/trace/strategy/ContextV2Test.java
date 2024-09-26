package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 적용
     */
    @Test
    void strategyV1() {
        ContextV2 context1 = new ContextV2();
        //context1.execute(() -> log.info("비지니스 로직1 실행"));
        context1.execute(new StrategyLogic1());

        ContextV2 context2 = new ContextV2();
        context2.execute(new StrategyLogic2());
    }

    /**
     * 전략 패턴 익명 내부 클래스
     */
    @Test
    void strategyV2() {
        //익명 내부 클래스 (인터페이스를 바로 구현) 활용
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    @Test
    void strategyV3() {
        //strategeV2() 메서드 코드 리팩토링 (람다식)
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비지니스 로직1 실행"));
        context.execute(() -> log.info("비지니스 로직2 실행"));
    }

}
