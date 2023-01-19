package com.example.mysqlpractice.domain.post;

import com.example.mysqlpractice.domain.post.entity.Post;
import com.example.mysqlpractice.domain.post.repository.PostRepository;
import com.example.mysqlpractice.util.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;


@SpringBootTest
public class PostBulkInsertTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                1L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2023, 2, 1)
        );

        var stopWatch = new StopWatch();
        stopWatch.start();

        var posts = IntStream.range(0, 10000 * 200)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        stopWatch.stop();

        System.out.println();
        System.out.println("객체생성 시간 " + stopWatch.getTotalTimeSeconds());
        System.out.println();



        var queryStopWatch = new StopWatch();

        queryStopWatch.start();
        postRepository.bulkInsert(posts);
        queryStopWatch.stop();

        System.out.println();
        System.out.println("DB 인서트 시간 " + queryStopWatch.getTotalTimeSeconds());
        System.out.println();

    }

}
