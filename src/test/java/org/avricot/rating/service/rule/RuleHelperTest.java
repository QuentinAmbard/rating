package org.avricot.rating.service.rule;

import junit.framework.Assert;

import org.avricot.rating.model.company.Fork;
import org.avricot.rating.repository.fork.ForkRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.Test;

public class RuleHelperTest {
    EasyMockSupport mocks = new EasyMockSupport();
    private final ForkRepository source = mocks.createMock(ForkRepository.class);
    private final RuleHelper helper = new RuleHelper(source);

    @Test
    public void testGetIndice() {
        mocks.resetAll();
        Fork f = new Fork();
        f.setValues("1|2|5|10|30");
        EasyMock.expect(source.findByName("test")).andReturn(f);
        mocks.replayAll();
        Assert.assertEquals(1F, helper.getIndice("test", 0));
        Assert.assertEquals(2F, helper.getIndice("test", 1));
        Assert.assertEquals(5F, helper.getIndice("test", 2));
        Assert.assertEquals(10F, helper.getIndice("test", 3));
        Assert.assertEquals(30F, helper.getIndice("test", 4));
        mocks.verifyAll();
    }

    @Test
    public void testVal() {
        mocks.resetAll();
        Fork f = new Fork();
        f.setValues("1|2|5|10|30");
        EasyMock.expect(source.findByName("test")).andReturn(f);
        mocks.replayAll();
        Assert.assertEquals(1, helper.getFork("test", 1.5F));
        Assert.assertEquals(5, helper.getFork("test", 500F));
        Assert.assertEquals(0, helper.getFork("test", 0.5F));
        Assert.assertEquals(1, helper.getFork("test", 1F));
        Assert.assertEquals(4, helper.getFork("test", 25F));
        Assert.assertEquals(5, helper.getFork("test", 30F));
        Assert.assertEquals(5, helper.getFork("test", 35F));
        Assert.assertEquals(4, helper.getFork("test", 10F));
        Assert.assertEquals(4, helper.getFork("test", 11F));
        mocks.verifyAll();
    }

    @Test
    public void testValDecroi() {
        mocks.resetAll();
        Fork f = new Fork();
        f.setValues("30|10|5|2|1");
        EasyMock.expect(source.findByName("test")).andReturn(f);
        mocks.replayAll();
        Assert.assertEquals(4, helper.getFork("test", 1.5F));
        Assert.assertEquals(0, helper.getFork("test", 500F));
        Assert.assertEquals(5, helper.getFork("test", 0.5F));
        Assert.assertEquals(4, helper.getFork("test", 1F));
        Assert.assertEquals(1, helper.getFork("test", 25F));
        Assert.assertEquals(0, helper.getFork("test", 30F));
        Assert.assertEquals(0, helper.getFork("test", 35F));
        Assert.assertEquals(1, helper.getFork("test", 10F));
        Assert.assertEquals(1, helper.getFork("test", 11F));
        Assert.assertEquals(5, helper.getFork("test", 0.5F));
        mocks.verifyAll();
    }

    @Test
    public void testValPercent() {
        mocks.resetAll();
        Fork f = new Fork();
        f.setValues("1|2|5|10");
        EasyMock.expect(source.findByName("test")).andReturn(f);
        mocks.replayAll();
        Assert.assertEquals(25, helper.getForkPercent("test", 1.5F));
        Assert.assertEquals(100, helper.getForkPercent("test", 500F));
        Assert.assertEquals(0, helper.getForkPercent("test", 0.5F));
        Assert.assertEquals(25, helper.getForkPercent("test", 1F));
        Assert.assertEquals(75, helper.getForkPercent("test", 8F));
        Assert.assertEquals(100, helper.getForkPercent("test", 10F));
        Assert.assertEquals(100, helper.getForkPercent("test", 11F));
        Assert.assertEquals(100, helper.getForkPercent("test", 30F));
        mocks.verifyAll();
    }
}
