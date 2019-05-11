package com.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConstraintImplTest.class,
        ControllerTest.class,
        MeanAllocatorImplTest.class,
        PropotionAllocatorImplTest.class,
        UserDataImplTest.class,
        ToyAllocatorApplicationTest.class
})
public class TestSuits {
}
