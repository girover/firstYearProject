 package services;

import database.repositories.RepositoryInterface;

/**
 * This abstract class is a part of Service Layer (Business Logic Layer).
 *
 * This class is a base class that must be inherited by all concrete resource
 * classes. This make it simple to access the repository object that is
 * responsible for those resources.
 * 
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github</a>
 */
public abstract class BaseResourceService implements ResourceServiceInterface {

	protected RepositoryInterface repository;
}
