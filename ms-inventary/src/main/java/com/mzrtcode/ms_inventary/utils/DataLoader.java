package com.mzrtcode.ms_inventary.utils;

import com.mzrtcode.ms_inventary.model.entity.Inventory;
import com.mzrtcode.ms_inventary.repository.InventaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final InventaryRepository inventaryRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading data...");
        if(inventaryRepository.count() == 0) {
            inventaryRepository.saveAll(
                    List.of(
                            Inventory.builder().sku("000001").quantity(10L).build(),
                            Inventory.builder().sku("000002").quantity(20L).build(),
                            Inventory.builder().sku("000003").quantity(30L).build(),
                            Inventory.builder().sku("000004").quantity(0L).build()
                    )
            );
        }
        log.info("Loading data complete.");
    }
}
