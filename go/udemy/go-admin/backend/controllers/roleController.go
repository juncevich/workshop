package controllers

import (
	"backend/database"
	"backend/model"
	"github.com/gofiber/fiber/v2"
	"strconv"
)

func AllRoles(c *fiber.Ctx) error {
	var roles []model.Role

	database.DB.Find(&roles)
	return c.JSON(roles)
}

func CreateRole(c *fiber.Ctx) error {
	var roleDto fiber.Map

	if err := c.BodyParser(&roleDto); err != nil {
		return err
	}

	list := roleDto["permissions"].([]interface{})
	permissions := make([]model.Permission, len(list))

	for i, permissionId := range list {
		id, _ := strconv.Atoi(permissionId.(string))
		permissions[i] = model.Permission{
			Id: uint(id),
		}
	}
	role := model.Role{
		Name:        roleDto["name"].(string),
		Permissions: permissions,
	}
	database.DB.Create(&role)

	return c.JSON(role)
}

func GetRole(c *fiber.Ctx) error {
	id, _ := strconv.Atoi(c.Params("id"))

	role := model.Role{
		Id: uint(id),
	}

	database.DB.Preload("Permissions").Find(&role)
	return c.JSON(role)
}

func UpdateRole(c *fiber.Ctx) error {

	id, _ := strconv.Atoi(c.Params("id"))

	var roleDto fiber.Map

	if err := c.BodyParser(&roleDto); err != nil {
		return err
	}

	list := roleDto["permissions"].([]interface{})
	permissions := make([]model.Permission, len(list))

	for i, permissionId := range list {
		id, _ := strconv.Atoi(permissionId.(string))
		permissions[i] = model.Permission{
			Id: uint(id),
		}
	}

	var result interface{}
	database.DB.Table("role_permissions").Where("role_id", id).Delete(&result)
	role := model.Role{
		Id:          uint(id),
		Name:        roleDto["name"].(string),
		Permissions: permissions,
	}

	database.DB.Model(&role).Updates(role)
	return c.JSON(role)
}

func DeleteRole(c *fiber.Ctx) error {
	id, _ := strconv.Atoi(c.Params("id"))

	role := model.Role{
		Id: uint(id),
	}

	database.DB.Delete(role)
	return nil
}
