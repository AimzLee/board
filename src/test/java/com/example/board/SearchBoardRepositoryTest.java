package com.example.board;

import com.example.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchBoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testSearch() {
        boardRepository.search();
    }

}
