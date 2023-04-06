<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
<section class="content-wrapper p-3">
            <div class="card card-default">
                <!-- /.card-header -->
                <div class="card-header">
                    <h3 class="card-title"> <strong> Store</strong>(Stock) <strong>List </strong> </h3>
                    <div class="card-tools">
                        <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
                            <i class="fas fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
                            <i class="fas fa-times"></i>
                        </button>
                    </div>
                </div>
                <div class="card-body p-0">
                    <table class="table table-striped projects">
                        <thead>
                            <tr>
                                <th style="width: 5%">Sr.No</th>
                                <th style="width: 17%">Project</th>
                                <th style="width: 17%">Category</th>
                                <th style="width: 17%">Grade</th>
                                <th style="width: 17%">Size</th>
                                <th style="width: 17%">Quantaty</th>
                                <th style="width: 17%"></th>
                            </tr>
                        </thead>
                        <tbody id="myTable">
                            <tr>
                                <td>1</td>
                                <td><a>solvent tank</a> <br></td>
                                <td><a>MS</a> <br></td>
                                <td><a>Flage</a> <br></td>
                                <td><a>100 X 100</a> <br></td>
                                <td><a>100</a> <br></td>
                                <td class="project-actions text-right">
                                    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
                                        data-target="#modal-projectDetails">
                                        <i class="fas fa-folder"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger btn-sm" data-toggle="modal"
                                        data-target="#modal-stockDelete" id="Delete1">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.card-body -->
            </div>
            <!-- <div class="modal fade" id="modal-projectDetails">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Stock Details</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="exampleInputEmail1">Project Id</label>
                                <p>Project 1</p>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Project Id</label>
                                    <p>Project 1</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <! /.modal-project show -
                </div>
            </div> -->
            <div class="modal fade" id="modal-stockDelete">
                <div class="modal-dialog">
                    <div class="modal-content bg-danger">
                        <div class="modal-header">
                            <h4 class="modal-title">Delete Stock</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true"></span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this Stock?</p>
                        </div>
                        <div class="modal-footer justify-content-between">
                            <button type="button" class="btn btn-outline-light" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-outline-light" id="deleteClicked">Delete Project</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </div>
</body>
</html>