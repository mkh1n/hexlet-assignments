@import gg.jte.Content
@import exercise.dto.LoginPage;
@param LoginPage page

@template.layout.page(
    content = @`
        <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h2 class="text-center mt-5">Login</h2>
                @if (page.getError() != null)
                    <div class="bg-danger text-white p-2 mb-3 rounded">
                        ${page.getError()}
                    </div>
                @endif
                <form action="/sessions" method="post">
                    <div class="form-group">
                        <label for="name">Логин</label>
                        <input type="text" class="form-control" id="name" name="name" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input type="password" class="form-control" id="password" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Войти</button>
                </form>
            </div>
        </div>
        </div>
   `,
)