package br.com.servers

import grails.converters.JSON

class ServidorController {

    static scaffold = Servidor
    def servidorService

    def ps(Servidor servidorInstance){
      def retorno = servidorService.listContainer(servidorInstance)
      render retorno as JSON
    }
    def images(Servidor servidorInstance){
      def retorno = servidorService.listImage(servidorInstance)
      render retorno as JSON
    }


    def inspect(){
      def servidor = Servidor.get(params.id)
      def containerID = params.containerID
      [result:servidorService.inspect(servidor,containerID),servidor:servidor]
    }

    def stop(){
        def servidor = Servidor.get(params.id)
        def containerID = params.containerID
        render servidorService.stop(servidor,containerID) as JSON
    }

    def start(){
        def servidor = Servidor.get(params.id)
        def containerID = params.containerID
        render servidorService.start(servidor,containerID) as JSON
    }

    def rm(){
        def servidor = Servidor.get(params.id)
        def containerID = params.containerID
        render servidorService.rm(servidor,containerID) as JSON
    }

}
