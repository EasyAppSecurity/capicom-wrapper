/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package by.creepid.capicom.wrapper;

import com.jacob.com.Dispatch;
import junit.framework.TestCase;

/**
 *
 * @author rusakovich
 */
public class CapicomStoreIntegrationTest extends TestCase {

    public CapicomStoreIntegrationTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getCertificates method, of class CapicomStore.
     */
    public void testGetCertificates() {
        System.out.println("**** getCertificates *****");
        CapicomStore store = new CapicomStore(2, "My", 2);

        assertNotNull(store.getCertificates());
        assertTrue(store.getCertificates().getCount() > 0);
        assertNotNull(store.getCertificates().getObject());
        assertNotNull(store.getCertificates().getAll());
        assertTrue(store.getCertificates().getAll().length > 0);
        assertNotNull(store.getCertificates().getAll()[0]);
        assertNotNull(store.getCertificates().getAll()[0].getSubjectName());
        assertNotNull(store.getCertificates().getAll()[0].export());
        assertNotNull(store.getCertificates().getAll()[0].export().length > 10);
    }

}
