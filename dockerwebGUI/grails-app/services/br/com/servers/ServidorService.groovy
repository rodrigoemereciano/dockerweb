package br.com.servers

import grails.transaction.Transactional
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse

@Transactional
class ServidorService {

    def listContainer(Servidor servidor) {
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/ps")
      return resp.json
    }

    def listImage(Servidor servidor) {
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/images")
      return resp.json
    }

    def inspect(Servidor servidor,String containerID){
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/inspect/${containerID}")
      return resp.json
    }

    def stop(Servidor servidor, String containerID){
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/stop/${containerID}")
      return resp.json
    }

    def start(Servidor servidor, String containerID){
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/start/${containerID}")
      return resp.json
    }

    def rm(Servidor servidor, String containerID){
      RestBuilder rest = new RestBuilder()
      RestResponse resp = rest.get("http://${servidor.IP}:${servidor.porta}/rm/${containerID}")
      return resp.json
    }
}
