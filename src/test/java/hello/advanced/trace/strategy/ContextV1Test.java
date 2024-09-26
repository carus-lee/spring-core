package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.stratege.ContextV1;
import hello.advanced.trace.strategy.code.stratege.Strategy;
import hello.advanced.trace.strategy.code.stratege.StrategyLogic1;
import hello.advanced.trace.strategy.code.stratege.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        //비지니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void strategyV1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 전략패턴 익명 내부 클래스2
     */
    @Test
    void strategyV2() {
        //익명 내부 클래스 (인터페이스를 바로 구현) 활용
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        ContextV1 context1 = new ContextV1(strategyLogic1);
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        ContextV1 context2 = new ContextV1(strategyLogic2);
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        context2.execute();
    }

    @Test
    void strategyV3() {
        //strategeV2() 메서드 코드 리팩토링
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    void strategyV4() {
        //strategeV3() 메서드 코드 리팩토링 (람다식)
        //람다는 인터페이스에 메서드 1개만 있을경우에만 가능.
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        context1.execute();

        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
        context2.execute();
    }

}
