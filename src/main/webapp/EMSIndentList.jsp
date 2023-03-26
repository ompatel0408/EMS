<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
	<jsp:include page="Header.jsp"></jsp:include>
	<jsp:include page="LeftSideBar.jsp"></jsp:include>
	<section class="content-wrapper p-3">
            <div class="card card-default">
                <!-- /.card-header -->
                <div class="card-header">
                    <h3 class="card-title"> <strong> Indent List </strong> </h3>
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
                    <table class="table table-striped projects overflow-scroll">
                        <thead>
                            <tr>
                                <th style="width: 2%">Sr.No</th>
                                <th style="width: 15%">Project</th>
                                <th style="width: 11%">Category</th>
                                <th style="width: 11%">Grade</th>
                                <th style="width: 11%">Size</th>
                                <th style="width: 11%">Item Name</th>
                                <th style="width: 11%">Quantaty</th>
                                <th style="width: 11%">Remark</th>
                                <th style="width: 9%"></th>
                            </tr>
                        </thead>
                        <tbody id="myTable">
                            <tr>
                                <td>1</td>
                                <td><a>Solvent Service tank</a> <br></td>
                                <td><a>MS</a> <br></td>
                                <td><a>Plage</a> <br></td>
                                <td><a>100 x 100</a> <br></td>
                                <td><a>Plate</a> <br></td>
                                <td><a>12</a> <br></td>
                                <td><a>added</a> <br></td>
                                <td class="project-actions text-right">
                                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal"
                                        data-target="#modal-editItem">
                                        <i class="fas fa-pencil-alt"></i>
                                    </button>
                                    <button type="button" class="btn btn-sm btn-danger" data-toggle="modal"
                                    data-target="#modal-stockDelete">
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
                            <h4 class="modal-title">Delete Indent</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true"></span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete this Indent?</p>
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