[33mcommit a4e5770745182740c2c292b33ead2c000e7273cf[m[33m ([m[1;36mHEAD -> [m[1;32mlesson27[m[33m)[m
Author: Evgeniy Vabishchevich <evgeniy.vabishevich.99@mail.ru>
Date:   Mon Feb 20 14:29:49 2023 +0300

    Placed classes in the appropriate packages

[33mcommit c3670b64777c924701ac8e041a7795762b7c4c2a[m
Author: Evgeniy Vabishchevich <evgeniy.vabishevich.99@mail.ru>
Date:   Mon Feb 20 14:29:04 2023 +0300

    Made displayed info update, when press 'remove' button

[33mcommit b67bfacb78b45d0916d0e6bedc208fffb5c37904[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 19 14:47:31 2023 +0300

    Changed button class, to make it look better

[33mcommit 2a402953a6a4a5b099a5f914ca55a2a342f3e359[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 19 14:37:19 2023 +0300

    Added baske.jsp page, witch displays selected products and gives you ability to remove some of them, if you want.

[33mcommit 6282782c5af16ef2927c46ab41ac5b797fd49261[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 19 14:31:04 2023 +0300

    Added ability to add products to basketMap, where 'key' = product id and 'value' = amount of selected products with this id

[33mcommit 6e3aa02a8fc27d28d47d456c8ded1720dccde531[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sat Feb 18 15:05:50 2023 +0300

    Created ability to connect to databse and two classes ProductsDAO,CategoryDAO, witch can get categories and products from database

[33mcommit 050ba2a00246809b63b76e273492628e730a0b82[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Thu Feb 16 23:03:51 2023 +0300

    Made private constructor for Constants class + small fix

[33mcommit 7180f2731619f8c520ddf3839dbf66121554c171[m[33m ([m[1;31morigin/main[m[33m, [m[1;31morigin/HEAD[m[33m, [m[1;32mmain[m[33m)[m
Merge: cdc682e 2600624
Author: tmspavel <91497593+tmspavel@users.noreply.github.com>
Date:   Wed Feb 15 17:47:59 2023 +0300

    Merge pull request #3 from EvgeniyVabishchevich/lesson27
    
    Lesson27

[33mcommit 260062456e6bbc8a5b9b0ddc9268d4744c9a11e8[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 23:08:21 2023 +0300

    Removed purchases check, since it's not implemented yet

[33mcommit 16957842bb8b8dcaac23f3545a15abc2f89e2c7e[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 23:03:06 2023 +0300

    Added validation for category existance by the given name

[33mcommit a8dfc612373c23cee63c3c339b95a2417b79743d[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 23:01:19 2023 +0300

    Created error404 page

[33mcommit 472c83951ba05a18dbf0e4ac312198b7bf3e823c[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 20:53:31 2023 +0300

    Created Constants class and moved there Login and Password constants

[33mcommit 4d2062fd21b17d471b73374cb350c165900746de[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 20:48:00 2023 +0300

    Small fixes

[33mcommit cdc682e0f5016325a04ce6246175e6b448054dbc[m
Merge: 6b26172 ed1dbbf
Author: tmspavel <91497593+tmspavel@users.noreply.github.com>
Date:   Sun Feb 12 15:41:17 2023 +0300

    Merge pull request #2 from EvgeniyVabishchevich/lesson27
    
    Lesson27

[33mcommit ed1dbbf70fbdd3d56c62e891a51c9ad7345bce99[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 14:03:30 2023 +0300

    Added user and purchases pages

[33mcommit 4625a553ac896d6e3b513da541b33df714050c16[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 13:51:38 2023 +0300

    Created Filter to redirect to login page, if user not authorised

[33mcommit 2b79ed4d8a7ae60e1f99cd4d59f219d4e9232b78[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 12:34:00 2023 +0300

    Created LoginServlet to validate user login and password

[33mcommit d0aac3655b4d3cf20e85cbb2554ff28f858ba330[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sun Feb 12 12:17:01 2023 +0300

    Small fixes after code review

[33mcommit 6b2617203f816b2bffb9282795080435ba3b4460[m
Merge: af83985 820b8a6
Author: tmspavel <91497593+tmspavel@users.noreply.github.com>
Date:   Sun Feb 12 08:18:31 2023 +0300

    Merge pull request #1 from EvgeniyVabishchevich/lesson27
    
    Upload of entire application on github

[33mcommit af839858f0dbb2d62f0d5838b47f068bd9c23980[m
Author: tmspavel <91497593+tmspavel@users.noreply.github.com>
Date:   Sat Feb 11 23:58:07 2023 +0300

    Update CODEOWNERS

[33mcommit 820b8a6f7ae40f3c018072e6f14b46373d6eb2a1[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sat Feb 11 23:37:55 2023 +0300

    Upload of entire application on github

[33mcommit b9912e55547a5a77d895d4cbcce9842250a38895[m
Author: natariyz <evgeniy.vabishevich.99@mail.ru>
Date:   Sat Feb 11 23:27:18 2023 +0300

    Tells gitinore witch files to ignore

[33mcommit de3e7576476a61e446ed5aa72b1fc82a5210e069[m
Author: Evgeniy.Vabishchevich <evgeniy.vabishevich.99@mail.ru>
Date:   Sat Feb 11 23:14:12 2023 +0300

    Create CODEOWNERS

[33mcommit e904f2ec6a1e89f4cd8f22d335577cb4dd5bd2c8[m
Author: Evgeniy.Vabishchevich <evgeniy.vabishevich.99@mail.ru>
Date:   Sat Feb 11 23:12:41 2023 +0300

    Initial commit
