

import org.junit.Assert;
import phoebe.ochkfplugin.app.bean.Ds2Sub;
import phoebe.ochkfplugin.app.bean.Sdr;
import phoebe.ochkfplugin.app.logic.Logic;
import org.junit.Test;
import phoebe.ochkfplugin.app.bean.UsageFee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pavarisa
 */
public class LogicTest {

    @Test
    public void isPackageFeeQuota() {
        Sdr sdr = new Sdr();
        sdr.setPackageType("R");
        sdr.setUserType("PPS");
        sdr.setResourceType("F");

        boolean isPackageFeeQuota = Logic.isPackageFreeQuota(sdr);
        Assert.assertTrue(isPackageFeeQuota);
    }

    @Test
    public void isPackageFeeQuota2() {
        Sdr sdr = new Sdr();
        sdr.setPackageType("R");
        sdr.setUserType("PPS");
        sdr.setResourceType("U");

        boolean isPackageFeeQuota = Logic.isPackageFreeQuota(sdr);
        Assert.assertFalse(isPackageFeeQuota);
    }

    @Test
    public void isPackageUnlimited() {
        Sdr sdr = new Sdr();
        sdr.setPackageType("R");
        sdr.setUserType("PPS");
        sdr.setResourceType("U");

        boolean isPackageUnlimited = Logic.isPackageUnlimited(sdr);
        Assert.assertTrue(isPackageUnlimited);
    }

    @Test
    public void isPackageUnlimited2() {
        Sdr sdr = new Sdr();
        sdr.setPackageType("R");
        sdr.setUserType("PPS");
        sdr.setResourceType("F");

        boolean isPackageUnlimited = Logic.isPackageUnlimited(sdr);
        Assert.assertFalse(isPackageUnlimited);
    }

    @Test
    public void isUFairPackage() {
        Sdr sdrFreeQuota = new Sdr();
        sdrFreeQuota.setPackageType("R");
        sdrFreeQuota.setUserType("PPS");
        sdrFreeQuota.setResourceType("U");
        sdrFreeQuota.setUfairFlag("true");

        Assert.assertTrue(Logic.isUFairPackage(sdrFreeQuota));

        Sdr sdrUnlimited = new Sdr();
        sdrUnlimited.setPackageType("R");
        sdrUnlimited.setUserType("PPS");
        sdrUnlimited.setResourceType("F");
        sdrUnlimited.setUfairFlag("true");

        Assert.assertTrue(Logic.isUFairPackage(sdrUnlimited));

    }
    
    @Test
    public void isUFairPackage2() {
        Sdr sdrFreeQuota = new Sdr();
        sdrFreeQuota.setPackageType("R");
        sdrFreeQuota.setUserType("PPS");
        sdrFreeQuota.setResourceType("U");
        sdrFreeQuota.setUfairFlag("false");
 
        Assert.assertFalse(Logic.isUFairPackage(sdrFreeQuota));

        Sdr sdrUnlimited = new Sdr();
        sdrUnlimited.setPackageType("R");
        sdrUnlimited.setUserType("PPS");
        sdrUnlimited.setResourceType("F");
        sdrUnlimited.setUfairFlag("false");

        Assert.assertFalse(Logic.isUFairPackage(sdrUnlimited));

    }

    @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRmms() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rmms");
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRsms() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rsms");
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRgprs_And_Ds2ValueTypeIsRgprs_And_gprsTimePricePerUnitMostThan0() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("1");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRgprs_And_Ds2ValueTypeIsRgprs_And_gprsVolumePricePerUnitMostThan0() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isRefunSubUnlimitPackage_RetureTrue_When_Ds2ValueTypeIsRgprs_And_Ds2ValueTypeIsRgprsIs0_And_gprsVolumePricePerUnitIs0() {

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);

        Assert.assertTrue(Logic.isSubUnlimitedPackage(ds2Sub));
    }
    
   @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRvoice_And_Ds2ValueTypeIsRgprs_And_gprsTimePricePerUnitMostThan0() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("1");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isSubFreeQuotaPackage_RetureTrue_When_Ds2ValueTypeIsRvoice_And_Ds2ValueTypeIsRgprs_And_gprsVolumePricePerUnitMostThan0() {
        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);
        Assert.assertTrue(Logic.isSubFreeQuotaPackage(ds2Sub));
    }

    @Test
    public void isRefunSubUnlimitPackage_RetureTrue_When_Ds2ValueTypeIsRvoice_And_Ds2ValueTypeIsRgprsIs0_And_gprsVolumePricePerUnitIs0() {

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);

        Assert.assertTrue(Logic.isSubUnlimitedPackage(ds2Sub));
    }

    @Test
    public void refunPackageIsTrueWhenFreeQuotaPackageNotUsed() {
        Sdr sdrFreeQuota = new Sdr();
        sdrFreeQuota.setPackageType("R");
        sdrFreeQuota.setUserType("PPS");
        sdrFreeQuota.setResourceType("F");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        sdrFreeQuota.getDs2Sub().add(ds2Sub);

        Assert.assertTrue(Logic.isRefundPackage(sdrFreeQuota));
    }

    @Test
    public void refunPackageIsTrueWhenUnlimitedNotUsed() {
        Sdr sdrUnlimited = new Sdr();
        sdrUnlimited.setPackageType("R");
        sdrUnlimited.setUserType("PPS");
        sdrUnlimited.setResourceType("U");
        sdrUnlimited.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2LastModT("20130101:081724");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);

        sdrUnlimited.getDs2Sub().add(ds2Sub);

        Assert.assertTrue(Logic.isRefundPackage(sdrUnlimited));
    }

    @Test
    public void refunPackageIsTrueWhenHybridPackageNotUsed() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");
        sdrHybridPackage.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2LastModT("20130101:081724");
        UsageFee usageFee2 = new UsageFee();
        usageFee2.setGprsTimePricePerUnit("0");
        usageFee2.setGprsVolumePricePerUnit("0");
        ds2Sub2.setUsageFee(usageFee2);

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertTrue(Logic.isRefundPackage(sdrHybridPackage));
    }

    @Test
    public void refunPackageIsFalseWhenHybridPackageIsUsed() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("90");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2LastModT("20130101:081724");

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertFalse(Logic.isRefundPackage(sdrHybridPackage));
    }

    @Test
    public void refunPackageIsFalseWhenHybridPackageIsUsed2() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");
        sdrHybridPackage.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2ValueType("rgprs");
        ds2Sub2.setDs2LastModT("20130101:100000");
        UsageFee usageFee2 = new UsageFee();
        usageFee2.setGprsTimePricePerUnit("0");
        usageFee2.setGprsVolumePricePerUnit("0");
        ds2Sub2.setUsageFee(usageFee2);

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertFalse(Logic.isRefundPackage(sdrHybridPackage));
    }
    
    @Test
    public void refunPackageIsTrueWhenFreeQuotaPackageNotUsed2() {
        Sdr sdrFreeQuota = new Sdr();
        sdrFreeQuota.setPackageType("R");
        sdrFreeQuota.setUserType("PPS");
        sdrFreeQuota.setResourceType("F");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        sdrFreeQuota.getDs2Sub().add(ds2Sub);

        Assert.assertTrue(Logic.isRefundPackage(sdrFreeQuota));
    }

    @Test
    public void refunPackageIsTrueWhenUnlimitedNotUsed2() {
        Sdr sdrUnlimited = new Sdr();
        sdrUnlimited.setPackageType("R");
        sdrUnlimited.setUserType("PPS");
        sdrUnlimited.setResourceType("U");
        sdrUnlimited.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        ds2Sub.setDs2LastModT("20130101:081724");
        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("0");
        ds2Sub.setUsageFee(usageFee);

        sdrUnlimited.getDs2Sub().add(ds2Sub);

        Assert.assertTrue(Logic.isRefundPackage(sdrUnlimited));
    }

    @Test
    public void refunPackageIsTrueWhenHybridPackageNotUsed2() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");
        sdrHybridPackage.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2LastModT("20130101:081724");
        UsageFee usageFee2 = new UsageFee();
        usageFee2.setGprsTimePricePerUnit("0");
        usageFee2.setGprsVolumePricePerUnit("0");
        ds2Sub2.setUsageFee(usageFee2);

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertTrue(Logic.isRefundPackage(sdrHybridPackage));
    }

    @Test
    public void refunPackageIsFalseWhenHybridPackageIsUsed3() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("90");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2LastModT("20130101:081724");

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertFalse(Logic.isRefundPackage(sdrHybridPackage));
    }

    @Test
    public void refunPackageIsFalseWhenHybridPackageIsUsed4() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");
        sdrHybridPackage.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rvoice");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2ValueType("rgprs");
        ds2Sub2.setDs2LastModT("20130101:100000");
        UsageFee usageFee2 = new UsageFee();
        usageFee2.setGprsTimePricePerUnit("0");
        usageFee2.setGprsVolumePricePerUnit("0");
        ds2Sub2.setUsageFee(usageFee2);

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertFalse(Logic.isRefundPackage(sdrHybridPackage));
    }
    
    @Test
    public void refunPackageIsFalseWhenHybridPackageIsUsed5() {
        Sdr sdrHybridPackage = new Sdr();
        sdrHybridPackage.setPackageType("R");
        sdrHybridPackage.setUserType("PPS");
        sdrHybridPackage.setResourceType("F");
        sdrHybridPackage.setStartDate("20130101:090500");

        Ds2Sub ds2Sub = new Ds2Sub();
        ds2Sub.setDs2ValueType("rgprs");
        ds2Sub.setDs2CounterValue("100");
        ds2Sub.setDs2ReplenishValue("100");

        UsageFee usageFee = new UsageFee();
        usageFee.setGprsTimePricePerUnit("0");
        usageFee.setGprsVolumePricePerUnit("1");
        ds2Sub.setUsageFee(usageFee);

        Ds2Sub ds2Sub2 = new Ds2Sub();
        ds2Sub2.setDs2ValueType("rvoice");
        ds2Sub2.setDs2LastModT("20130101:100000");
        UsageFee usageFee2 = new UsageFee();
        usageFee2.setGprsTimePricePerUnit("0");
        usageFee2.setGprsVolumePricePerUnit("0");
        ds2Sub2.setUsageFee(usageFee2);

        sdrHybridPackage.getDs2Sub().add(ds2Sub);
        sdrHybridPackage.getDs2Sub().add(ds2Sub2);

        Assert.assertFalse(Logic.isRefundPackage(sdrHybridPackage));
    }
}
