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
        <div class="box box-info" id="inspectContainer">
          <div class="box-header with-border">
            <h3 class="box-title">Container: ${result[0].Config.Image}</h3>
            <div class="box-tools pull-right">
              <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
              <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
            </div>
          </div><!-- /.box-header -->
          <div class="box-body">
            <div class="table-responsive">
              <table class="table no-margin">
                <thead>
                  <tr>
                    <th>Portas utilizadas</th>
                    <th>Diretorio de Trabalho</th>
                    <th>Status</th>
                    <th>ID</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td><a href="pages/examples/invoice.html">${result[0].NetworkSettings.Ports}</a></td>
                    <td>${result[0].Config.WorkingDir}</td>
                    <td><span class="label label-success">${result[0].State.Status}</span></td>
                    <td><div class="sparkbar" data-color="#00a65a" data-height="20">${result[0].Id}</div></td>
                  </tr>

                </tbody>
              </table>
            </div><!-- /.table-responsive -->
          </div><!-- /.box-body -->
          <div class="box-footer clearfix">

            <div class="box-footer clearfix" id="startContainer">
                <input type=button v-on:click="start()" class="btn btn-sm btn-success btn-flat pull-left" value="Iniciar Imagem">
            </div>


            <div class="box-footer clearfix" id="stopContainer">
                <input type=button v-on:click="stop()" class="btn btn-sm btn-info btn-flat pull-middle" value="Parar Container">
            </div>

            <div class="box-footer clearfix" id="rmContainer">
                <input type=button v-on:click="rm()" class="btn btn-sm btn-danger btn-flat pull-left" value="Remover Container">
            </div>

          </div><!-- /.box-footer -->


        </div><!-- /.box -->


        <asset:script type="text/javascript">
          var dadosServidor = {servidor:${servidor.id},container:'${result[0].Id}'};
        </asset:script>
    </body>
</html>
