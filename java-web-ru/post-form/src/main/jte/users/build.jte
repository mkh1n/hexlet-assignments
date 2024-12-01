@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <h2>Форма регистрации</h2>
            <form action="/users" method="post">
                <div class="form-group">
                    <label for="firstName">Имя</label>
                    <input type="text" class="form-control" name="firstName" placeholder="Введите ваше имя">
                </div>
                <div class="form-group">
                    <label for="lastName">Фамилия</label>
                    <input type="text" class="form-control" name="lastName" placeholder="Введите вашу фамилию">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" placeholder="Введите ваш email">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password" class="form-control" name="password" placeholder="Введите пароль">
                </div>
                <button type="submit" class="btn btn-primary">Отправить</button>
            </form>
    `
)
