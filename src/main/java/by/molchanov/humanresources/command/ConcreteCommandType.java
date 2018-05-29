package by.molchanov.humanresources.command;

import by.molchanov.humanresources.command.admin.*;
import by.molchanov.humanresources.command.aspirant.JobRequestRegistrationCommand;
import by.molchanov.humanresources.command.aspirant.OrgRegistrationCommand;
import by.molchanov.humanresources.command.common.*;
import by.molchanov.humanresources.command.director.*;

import static by.molchanov.humanresources.command.ResponseType.FORWARD;

/**
 * Enum {@link ConcreteCommandType} contains all application commands.
 * Used for getting necessary command object and representation type.
 *
 * @author Molchanov Vladislav
 */
public enum ConcreteCommandType {
    AUTHENTICATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return AuthenticationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    USER_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return UserRegistrationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    LOG_OUT {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return LogOutCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    ORG_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return OrgRegistrationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    VACANCY_FILTER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return VacancyFilterCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    REQUEST_FILTER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return RequestFilterCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    VACANCY_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return VacancyRegistrationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },

    REQUEST_REGISTRATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return JobRequestRegistrationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    CONFIRM_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return ConfirmVacancyCommand.getIstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    DELETE_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return DeleteVacancyCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    EST_BELORUSSIAN_LOCALE {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return EstablishBelorussianLocaleCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    EST_RUSSIAN_LOCALE {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return EstablishRussianLocaleICommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    EST_ENGLISH_LOCALE {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return EstablishEnglishLocaleCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    SEND_REQUEST_ANSWER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return SendRequestAnswerCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    DELETE_USER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return DeleteUserCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    CLOSE_REQUEST {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return CloseRequestCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    CLOSE_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return CloseVacancyCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    CLOSE_OLD_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return CloseOldVacancyCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    FILL_CONTENT {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return FillContentCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    RISE_TO_ADMIN {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return RiseToAdminCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    EDIT_VACANCY {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return EditVacancyCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    EDIT_USER {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return EditUserCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    },
    DELETE_ORGANIZATION {
        @Override
        public ConcreteCommand getConcreteCommandBroker() {
            return DeleteOrganizationCommand.getInstance();
        }

        @Override
        public ResponseType getResponseType() {
            return FORWARD;
        }
    };

    public abstract ConcreteCommand getConcreteCommandBroker();
    public abstract ResponseType getResponseType();
}
