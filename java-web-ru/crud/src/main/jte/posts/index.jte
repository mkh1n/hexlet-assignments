@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes

@param PostsPage page

@template.layout.page(
    content = @`
        <div class="container mt-5">
            <div class="row">
                @for (var post : page.getPosts())
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <div class="card-body">
                                <a class="card-title" href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                            </div>
                        </div>
                    </div>
               @endfor
               </div>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath(page.getPage() - 1)}">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath(page.getPage())}">${page.getPage()}</a></li>
                    <li class="page-item"><a class="page-link" href="${NamedRoutes.postsPath(page.getPage() + 1)}">Next</a></li>
                </ul>
            </nav>
        </div>
    `,
)
