@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes


@param PostPage page

@template.layout.page(
content = @`
        <div class="container mt-5">
            <div class="row">
                <div class="col-12">
                    <h1 class="display-4">${page.getPost().getName()}</h1>
                </div>
            </div>

            <!-- Post Content -->
            <div class="row mt-4">
                <div class="col-12">
                    ${page.getPost().getBody()}
                </div>
            </div>

            <!-- Back Button -->
            <div class="row mt-4">
                <div class="col-12">
                    <a class="btn btn-primary" href="${NamedRoutes.postsPath()}">Назад</a>
                </div>
            </div>
        </div>
    `
)
