package org.interview.aequilibrium.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Model Transformer test.
 */
class TransformerTest {

    /**
     * Test Transformer Type creation.
     */
    @Test
    void creteTransformerTypeValueOfByCodeTest() {
        String codeAutobot = "A";
        String codeDecepticon = "D";
        String codeWrong = "AUTOBOT";

        assertEquals(Transformer.TransformerType.AUTOBOT, Transformer.TransformerType.valueOfByCode(codeAutobot).get());
        assertEquals(Transformer.TransformerType.DECEPTICON, Transformer.TransformerType.valueOfByCode(codeDecepticon).get());
        assertFalse(Transformer.TransformerType.valueOfByCode(codeWrong).isPresent());
        assertFalse(Transformer.TransformerType.valueOfByCode(null).isPresent());
    }

}