package com.luc.base.suite

import com.luc.base.ExampleInstrumentedTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

// Runs all unit tests.
@RunWith(Suite::class)
@Suite.SuiteClasses(ExampleInstrumentedTest::class)
class UnitTestSuite