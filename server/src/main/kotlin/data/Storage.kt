package data

import domain.Employee
import domain.Tool
import domain.ToolType

val tool1 = Tool(
    code = "1111-1111",
    name = "Cutter",
    description = "Cutter tool",
    type = ToolType.CUTTER,
    photoUrl = "https://biface.ru/upload/iblock/6f8/6f8723bde2780090591a3f9a8c94d9cf.png",
)

val tool2 = Tool(
    code = "2222-2222",
    name = "Milling",
    description = "Milling tool",
    type = ToolType.MILLING,
    photoUrl = "https://fr.osgeurope.com/sites/osg-corporate.dev/files/content/Page/Industry-solutions/Job-shop/JobShop_Milling.jpg",
)

val tool3 = Tool(
    code = "3333-3333",
    name = "Drill",
    description = "Drill tool",
    type = ToolType.DRILL,
    photoUrl = "https://media.rs-online.com/image/upload/bo_1.5px_solid_white,b_auto,c_pad,dpr_2,f_auto,h_399,q_auto,w_710/c_pad,h_399,w_710/F4679060-01?pgw=1",
)

val tool4 = Tool(
    code = "4444-4444",
    name = "Holder",
    description = "Holder tool",
    type = ToolType.HOLDER,
    photoUrl = "https://www.rinscom.com/upload/iblock/e61/pl.png",
)

val tool5 = Tool(
    code = "5555-5555",
    name = "Inner",
    description = "Inner tool",
    type = ToolType.INNER,
    photoUrl = "https://www.itm-shop.ru/upload/iblock/c7f/1eqz03c3mhmzxw24zpo1f4vffhh4hob1/CNMG_M_GOLD_10600.png",
)


val employee1 = Employee(
    id = "1",
    name = "John",
    surname = "Smith",
    photoUrl = "https://business.providence.edu/wp-content/uploads/sites/138/2023/08/KT-9453.jpg",
    tools = listOf(tool3, tool2),
)

val employee2 = Employee(
    id = "2",
    name = "Ivan",
    surname = "Ivanov",
    photoUrl = "https://cdn.er.ru/media/people/member_photos_site/photos_site_mentions_671/photo-1595846323.jpg",
    tools = listOf(tool1, tool4, tool5),
)

val employee3 = Employee(
    id = "3",
    name = "Michael",
    surname = "Krauz",
    photoUrl = "https://pliki.ptwp.pl/pliki/01/54/79/015479_r1_300.jpg",
    tools = listOf(tool1, tool3),
)
