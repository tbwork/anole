package org.anole.infrastructure.repository;

import org.anole.infrastructure.model.AnoleEnvironment;

import java.util.List;

public interface IEnvironmentRepository {

	List<AnoleEnvironment> getEnviroments();

}
