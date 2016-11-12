<!DOCTYPE html>
<html>
    <head>
      <meta name="layout" content="main" />
      <g:set var="entityName" value="${message(code: 'servidor.label', default: 'Servidor')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <!--<div id="listContainer">
          <div class="col-sm-3" v-for="container in listaContainer">
            <div class="panel panel-default" >
              <div class="panel-heading">
                <h3 class="panel-title">{{container.id}}</h3>
              </div>
              <div class="panel-body">
                Panel content {{container.command}}

              </div>
            </div>
          </div>
        </div>-->
        <div id="listContainer">
        <div class="col-md-3 col-sm-6 col-xs-12" v-for="container in listaContainer">
              <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>
                <div class="info-box-content">
                  <span class="info-box-text">{{container.command}}</span>
                  <span class="info-box-number">Id:{{container.id}}</span>

                <a href="${createLink(action:'inspect',controller:'servidor')}/{{servidor.id}}?containerID={{container.id}}" class="btn btn-default navbar-btn">+Info</a>
                </div><!-- /.info-box-content -->
              </div><!-- /.info-box -->
        </div>
      </div>
    </body>
</html>
