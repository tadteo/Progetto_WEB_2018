<!--    
        <div class="row">
            <div class="col-6">
                <form class="form-signin text-center" action="/cinema/" method="GET">
                                        <h1><button class="astext">Cinema Universe</button></h1>
                                </form>
                                <form class="form-signin text-center" action="/cinema/" method="POST">
                  <h3>
                      <button class="astext" name="pageRequested" value="infopage">Info cinema</button>
                  </h3>
                                </form>
            </div>
            <div class="col-6">
<c:choose>
    <c:when test="${sessionScope.email != null}">
        <c:set var="emailParts" value="${fn:split(sessionScope.email, '@')}" />
        <h3>Welcome ${emailParts[0]} <b>(${sessionScope.ruolo})</b></h3>
        <h4>You are logged in</h4>

        <form class="form-signin" action="/cinema/logout.do" method="POST">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
        </form>
    </c:when>
    <c:otherwise>
        <form class="form-signin" action="/cinema/login.do" method="GET">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login/Sign Up</button>
    </c:otherwise>      
</c:choose>
</div>
</div>-->