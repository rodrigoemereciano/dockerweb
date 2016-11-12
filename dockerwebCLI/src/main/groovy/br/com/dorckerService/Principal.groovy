package br.com.dorckerService

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import org.slf4j.Logger
import org.slf4j.LoggerFactory


public class Principal extends AbstractVerticle{


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    void start(){
        def server = vertx.createHttpServer()
        def router = Router.router(vertx)

        router.route("/ps").handler({ routingContext ->
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerPS()))
        })

        router.route("/images").handler({ routingContext ->
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerImages()))
        })

        router.route("/images/rmi/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerRmi(id)))
        })
        router.route("/images/rmi/f/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerRmi(id,true)))
        })
        router.route("/rm/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerRm(id)))
        })
        router.route("/rm/f/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerRm(id,true)))
        })

        router.route("/stop/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.stop(id)))
        })
        router.route("/start/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.start(id)))
        })

        router.route("/inspect/:id").handler({ routingContext ->
            def id = routingContext.request().getParam("id")
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(DockerCommands.inspect(id))
        })
        router.route("/ports").handler({ routingContext ->
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(DockerCommands.dockerPorts()))
        })

        router.route("/machine/os/time").handler({ routingContext ->
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(MachineCommands.obterDataHoraAtual()))
        })

        router.route("/machine/os/name").handler({ routingContext ->
            routingContext.response()
                    .putHeader("content-type","application/json; charset=utf-8")
                    .end(Json.encodePrettily(MachineCommands.osName()))
        })

        server.requestHandler(router.&accept).listen(8081)
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(Principal.class.getName());
    }
}