package br.com.dorckerService

import org.newsclub.net.unix.AFUNIXSocket
import org.newsclub.net.unix.AFUNIXSocketAddress

/**
 * Created by romulofc on 22/04/16.
 */
class DockerCommands {


    private static final DOCKER_PS = ["sudo","docker","ps","-a","--format","\"{{.ID}}|{{.Image}}|{{.Command}}|{{.CreatedAt}}|{{.RunningFor}}\""]
    private static final DOCKER_STOP = ["sudo","docker","stop"]
    private static final DOCKER_START = ["sudo","docker","start"]
    private static final DOCKER_INSPECT = ["sudo","docker","inspect"]
    private static final DOCKER_RM = ["sudo","docker","rm"]
    private static final DOCKER_RMI = ["sudo","docker","rmi"]
    private static final DOCKER_IMAGES = ["sudo","docker","images","--format","\"{{.ID}}|{{.Repository}}|{{.Tag}}|{{.CreatedSince}}|{{.CreatedAt}}|{{.Size}}\""]
    private static final DOCKER_PORTS = ["sudo","docker","ps","--format","\"{{.ID}}|{{.Image}}|{{.Ports}}\""]









    

    private static Process createProcess(List listaComando){
        ProcessBuilder pb = new ProcessBuilder(listaComando)
        def os = System.getProperty("os.name").toLowerCase()
        if(os.indexOf("mac")>=0){
            "bash --login '/Applications/Docker/Docker Quickstart Terminal.app/Contents/Resources/Scripts/start.sh'".execute()
            pb.environment().put("DOCKER_CERT_PATH","/Users/romulofc/.docker/machine/machines/default")
            pb.environment().put("DOCKER_HOST","tcp://192.168.99.100:2376")
            pb.environment().put("DOCKER_MACHINE_NAME","default")
            pb.environment().put("DOCKER_TLS_VERIFY","1")
        }

        return pb.start()
    }

    public static stop(containerID){
        def listaComando = []
        listaComando.addAll(DockerCommands.DOCKER_STOP)
        listaComando.add(containerID)
        Process p = DockerCommands.createProcess(listaComando)
        def error =  p?.errorStream?.text
        def success =  p?.text
        p.waitFor()
        p.destroy()
        def exitValue = p.exitValue()
        return [exitValue:exitValue,error:error,success:success]

    }
    public static dockerRmi(containerID,force=false){
        def listaComando = []
        listaComando.addAll(DockerCommands.DOCKER_RMI)
        if(force)
            listaComando.add("-f")
        listaComando.add(containerID)
        Process p = DockerCommands.createProcess(listaComando)
        def error =  p?.errorStream?.text
        def success =  p?.text
        p.waitFor()
        p.destroy()
        def exitValue = p.exitValue()
        return [exitValue:exitValue,error:error,success:success]

    }

    public static dockerRm(containerID,force=false){
        def listaComando = []
        listaComando.addAll(DockerCommands.DOCKER_RM)
        if(force)
            listaComando.add("-f")
        listaComando.add(containerID)
        Process p = DockerCommands.createProcess(listaComando)
        def error =  p?.errorStream?.text
        def success =  p?.text
        p.waitFor()
        p.destroy()
        def exitValue = p.exitValue()
        return [exitValue:exitValue,error:error,success:success]

    }

   /* public static testeSocket(){
        File socketFile = new File("/var/run/docker.sock")
        AFUNIXSocket sock = AFUNIXSocket.newInstance()
        sock.connect(new AFUNIXSocketAddress(socketFile))

    }*/

    public static dockerImages(){
        Process p = DockerCommands.createProcess(DockerCommands.DOCKER_IMAGES)
        def listaResult = []

        final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))
        String line = r.readLine()
        while (line != null) {
            def tratado = line.split("\\|")
            listaResult.add([id:tratado[0].replaceAll("\"",""),repository:tratado[1].replaceAll("\"",""),tag:tratado[2].replaceAll("\"",""),createdSince:tratado[3].replaceAll("\"",""),size:tratado[4].replaceAll("\"","")])
            line = r.readLine()
        }
        r.close()

        p.waitFor()
        p.destroy()
        return listaResult
    }

    public static start(containerID){
        def listaComando = []
        listaComando.addAll(DockerCommands.DOCKER_START)
        listaComando.add(containerID)
        Process p = DockerCommands.createProcess(listaComando)
        def error =  p?.errorStream?.text
        def success =  p?.text
        p.waitFor()
        p.destroy()
        def exitValue = p.exitValue()
        return [exitValue:exitValue,error:error,success:success]

    }
    public static inspect(containerID){
        def listaComando = []
        listaComando.addAll(DockerCommands.DOCKER_INSPECT)
        listaComando.add(containerID)
        Process p = DockerCommands.createProcess(listaComando)
        def result = p.text
        p.waitFor()
        p.destroy()
        return result

    }

    public static dockerPS() {
        Process p = DockerCommands.createProcess(DockerCommands.DOCKER_PS)
        def listaResult = []

        final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))
        String line = r.readLine()
        while (line != null) {
            def tratado = line.split("\\|")
            listaResult.add([id:tratado[0].replaceAll("\"",""),image:tratado[1].replaceAll("\"",""),command:tratado[2].replaceAll("\"",""),createAt:tratado[3].replaceAll("\"",""),runningFor:tratado[4].replaceAll("\"","")])
            line = r.readLine()
        }
        r.close()

        p.waitFor()
        p.destroy()
        return listaResult
    }

    public static dockerPorts() {
        Process p = DockerCommands.createProcess(DockerCommands.DOCKER_PORTS)
        def listaResult = []

        final BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))
        String line = r.readLine()
        while (line != null) {
                def tratado = line.split("\\|")
                listaResult.add([id: tratado[0].replaceAll("\"", ""), image: tratado[1].replaceAll("\"", ""), ports: tratado[2].replaceAll("\"", "")])
                line = r.readLine()
            }
        r.close()

        p.waitFor()
        p.destroy()
        return listaResult
     }

}
