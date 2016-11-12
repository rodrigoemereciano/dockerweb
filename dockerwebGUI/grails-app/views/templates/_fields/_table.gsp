<table class="table table-responsive table-hover">
    <thead>
         <tr>
            <g:each in="${domainProperties}" var="p" status="i">
                <g:if test="${!(p.name.startsWith("senha") || p.name.endsWith("Ascii"))}">
                    <g:set var="propTitle">${domainClass.propertyName}.${p.name}.label</g:set>
                    <g:sortableColumn property="${p.name}" title="${message(code: propTitle, default: p.naturalName)}" />
                </g:if>
            </g:each>
            <td>&nbsp;</td>
        </tr>
    </thead>
    <tbody>
        <g:each in="${collection}" var="bean" status="i">
                <g:each in="${domainProperties}" var="p" status="j">

                        <td><f:display bean="${bean}" property="${p.name}" displayStyle="${displayStyle?:'table'}" /></td>
                </g:each>
                <td>
                    <g:form resource="${bean}" method="DELETE">
                            <g:link class="btn btn-primary" action="edit" resource="${bean}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                            <input class="btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('Deseja remover mesmo?');" />
                    </g:form>
                </td>
            </tr>
        </g:each>
    </tbody>
</table>
