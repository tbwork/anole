package org.tbwork.anole.server.basic.repository;

import java.util.List;

import org.anole.infrastructure.model.AnoleEnvironment;

public interface IEnvironmentRepository {

	List<AnoleEnvironment> getEnviroments();
}
