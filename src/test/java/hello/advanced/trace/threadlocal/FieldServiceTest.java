package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FiledService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FiledService filedService = new FiledService();

    @Test
    void test() {
        log.info("main start");

        //람다식
        Runnable userA = () -> {
            filedService.logic("userA");
        };
        //기본방식
        Runnable userB = new Runnable() {
            @Override
            public void run() {
                filedService.logic("userB");
            }
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");


        threadA.start();
        //sleep(2000); //A실행이 완전히 끝내고 B실행 (동시성문제 X)
        sleep(100); //A실행이 끝내기 전 B실행 (동시성문제 O)
        threadB.start();

        sleep(3000); //메인쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
