 package services;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.management.RuntimeErrorException;

import app.configs.Config;
import database.entities.Entity;
import database.repositories.Repository;
import database.repositories.RepositoryInterface;

/**
 * This abstract class is a part of Service Layer (Business Logic Layer).
 *
 * This class is a base class that must be inherited by all concrete resource
 * classes. This make it simple to access the repository object that is
 * responsible for those resources.
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseResourceService implements ResourceServiceInterface {

	protected RepositoryInterface repository;
}
