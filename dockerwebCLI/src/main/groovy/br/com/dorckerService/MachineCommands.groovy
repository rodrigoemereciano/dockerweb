package br.com.dorckerService

import java.lang.management.ManagementFactory
import java.lang.management.OperatingSystemMXBean

/**
 * Created by romulofc on 22/04/16.
 */

    class MachineCommands {

    private static final OperatingSystemMXBean osMBean

    static {
        try {
            osMBean = ManagementFactory.newPlatformMXBeanProxy(ManagementFactory.getPlatformMBeanServer(), ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME, OperatingSystemMXBean.class)
        } catch (IOException e) {
            throw new RuntimeException(e)
        }
    }

    public static obterDataHoraAtual() {
        def dataHoraAtual = System.currentTimeMillis()
        return ["dataHoraAtual":dataHoraAtual,"dataHoraAtualFormatada":new Date(dataHoraAtual).format("dd/MM/yyyy HH:mm:ss")]
    }
    public static obterLoadAverage() {
        return osMBean.systemLoadAverage
    }

    public static osName() {
        return ["name":osMBean.name,"version":osMBean.version,"arch":osMBean.arch]
    }

}
