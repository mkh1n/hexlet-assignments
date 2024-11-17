@import exercise.dto.users.UserPage;
@param UserPage user


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4 offset-md-4">
                <div class="card text-center">
                    <div class="card-header">
                        <h3>User Profile</h3>
                    </div>
                    <div class="card-body">
                        <img src="https://via.placeholder.com/150" class="rounded-circle mb-3" alt="Avatar">
                        <h4 class="card-title">${user.getUser().getFirstName()} ${user.getUser().getLastName()}</h4>
                        <p class="card-text">${user.getUser().getEmail()}</p>
                    </div>
                    <div class="card-footer text-muted">
                        <p>Member since 2023</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
