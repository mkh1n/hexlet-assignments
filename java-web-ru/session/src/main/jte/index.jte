@import gg.jte.Content
@import exercise.dto.MainPage;
@param MainPage page

@template.layout.page(
    content = @`
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2 class="text-center mt-5">Welcome</h2>
                    @if (page.getName() == null)
                        <p class="text-center">
                            <a href="/sessions/build" class="btn btn-primary">Войти</a>
                        </p>
                     @else
                        <p class="text-center">
                            Welcome, ${page.getName().toString()}!
                        </p>
                        <form action="/sessions/delete" method="post">
                            <button type="submit" class="btn btn-danger btn-block">Выйти</button>
                        </form>

                    @endif
                </div>
            </div>
        </div>
   `,
)