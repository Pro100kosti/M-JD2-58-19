<jsp:include page="header.jsp"/>

<h1>Register new user</h1>

<form method="POST" action="${pageContext.request.contextPath}/registration">
    <div class="form-group">
        <label for="firstName">First name</label>
        <input type="text" name="firstName" class="form-control" id="firstName" placeholder="Enter first name">
        <small class="form-text text-muted">Please enter you first name</small>
    </div>
    <div class="form-group">
        <label for="lastName">Last name</label>
        <input type="text" name="lastName" class="form-control" id="lastName" placeholder="Enter last name">
        <small class="form-text text-muted">Please enter you last name</small>
    </div>
    <div class="form-group">
        <label for="email">Your email</label>
        <input type="email" name="email" class="form-control" id="email" placeholder="Enter your email">
        <small class="form-text text-muted">Your email will be used for login</small>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="Your password">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<jsp:include page="footer.jsp"/>