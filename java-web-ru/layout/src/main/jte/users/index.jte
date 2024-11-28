@import exercise.dto.users.UsersPage;
@param UsersPage page

@template.layout.page(
    content = @`
        <h1 class="text-center">User List</h1>
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th></th>

                        </tr>
                    </thead>
                    <tbody>
                        @for (var user : page.getUsers())
                            <tr>
                                <td>${user.getId()}</td>
                                <td>${user.getFirstName()}</td>
                                <td>${user.getLastName()}</td>
                                <td>${user.getEmail()}</td>
                                <td>
                                    <a href="/users/${user.getId()}" class="btn btn-primary">View</a>
                                </td>
                            </tr>

                        @endfor
                    </tbody>
                </table>
    `,
)