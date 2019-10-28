var FakeListAPI = /** @class */ (function () {
    function FakeListAPI() {
        this.lists = [];
        this.loadLists();
    }
    // emulate async API call - Gets all lists.
    FakeListAPI.prototype.getLists = function () {
        return Promise.resolve(_.cloneDeep(this.lists));
    };
    // emulate async API call - Gets single full card.
    FakeListAPI.prototype.getCard = function (id) {
        for (var l = 0; l < this.lists.length; l++) {
            var list = this.lists[l];
            for (var i = 0; i < list.cards.length; i++) {
                var card = list.cards[i];
                if (card.id === id) {
                    return Promise.resolve(card);
                }
            }
        }
        return Promise.reject('ERROR 404');
    };
    FakeListAPI.prototype.loadLists = function () {
        var order = 65535;
        this.lists = [
            {
                id: '_new',
                title: 'Visitante',
                cards: [
                    {
                        id: 'bitbucket://repositories/example/pp-ui/issues/132',
                        title: 'Curso de Hotelaria',
                        order: ++order,
                        provider: 'bitbucket',
                        type: 'bug',
                        severity: 'high',
                        prefix: 'pp-ui',
                        editUrl: 'https://bitbucket.org/example/pp-ui/issues/132'
                    },
                    {
                        id: 'slack://example/ABCDEFG12/j12345678901234567',
                        title: 'Message!',
                        order: ++order,
                        provider: 'slack',
                        type: 'comment-private',
                        prefix: '#mgmt-im',
                        viewUrl: 'https://example.slack.com/archives/ABCDEFG12/j12345678901234567',
                        tags: ['tag n', 'tag n+1', 'tag n+2'],
                        isWatching: true,
                        hasComments: true,
                        dueDate: new Date()
                    },
                    {
                        id: 'zendesk://example/tickets/1234567890',
                        title: 'This ticket has a very long description which should wrap to at least two lines for demo purposes.',
                        order: ++order,
                        provider: 'zendesk',
                        type: 'ticket',
                        prefix: 'gdpr@',
                        editUrl: 'https://example.zendesk.com/tickets/1234567890',
                        tags: ['urgent', 'unassigned', 'tag n', 'tag n+1', 'tag n+2'],
                        hasDescription: true
                    },
                    {
                        id: 'gdrive://example.com/d/1234567890/',
                        title: 'New folder request for "Eyes" by @JDoe.',
                        order: ++order,
                        provider: 'google-drive',
                        type: 'folder',
                        viewUrl: 'https://drive.google.com/d/1234567890'
                    },
                    {
                        id: 'gdrive://example.com/d/1234567890/',
                        title: 'Check back in 3 days.',
                        order: ++order,
                        provider: 'trello',
                        type: 'card',
                        prefix: 'lna-cases',
                        viewUrl: 'https://drive.google.com/d/1234567890'
                    },
                    {
                        id: 'gcal://example.com/cal/12345/event/1234567890/',
                        title: 'Management Meeting (part 3)',
                        order: ++order,
                        provider: 'calendar',
                        type: 'calendar-event',
                        prefix: 'Work Calendar',
                        viewUrl: 'https://calendar.google.com/c/12345/event/1234567890'
                    },
                    {
                        id: 'gkeep://keep/12345/note/1234567890',
                        title: 'Notes from Site',
                        order: ++order,
                        provider: 'google-keep',
                        type: 'note',
                        viewUrl: 'https://keep.google.com/keep/12345/note/1234567890'
                    },
                    {
                        id: 'imap://gmail.com/jdoe@example.com/#inbox/some-long-mail-id',
                        title: 'Hello from Nigeria!',
                        order: ++order,
                        provider: 'email',
                        type: 'message',
                        prefix: 'Inbox',
                        viewUrl: 'https://mail.google.com/mail/#inbox/some-long-mail-id'
                    }
                ]
            },
            {
                id: '_todo',
                title: 'Lead',
                cards: [
                    {
                        id: '_test_delete',
                        title: 'Try drag this (Look the console)',
                        order: ++order
                    },
                    {
                        id: _.uniqueId('card-'),
                        title: 'Try Click This!',
                        order: ++order
                    }
                ].concat((_.times(10, function (i) { return ({
                    id: "dummy-" + i,
                    title: "Dummy card title " + i,
                    order: ++order
                }); })))
            },
            {
                id: '_working',
                title: 'Inscrito Parcial',
                cards: [
                    {
                        id: _.uniqueId('card-'),
                        "title": "Do Something!",
                        order: ++order
                    },
                    {
                        id: _.uniqueId('card-'),
                        "title": "Run?",
                        order: ++order
                    }
                ]
            },
            {
                id: '_done',
                title: 'Matriculado',
                cards: [
                    {
                        id: _.uniqueId('card-'),
                        title: 'All right',
                        order: ++order
                    },
                    {
                        id: _.uniqueId('card-'),
                        title: 'Ok!',
                        order: ++order
                    }
                ]
            },
        ];
    };
    FakeListAPI.genCardOrder = function (prev, next) {
        return (prev && next) ? (prev + next) / 2 // isBetween
            : prev ? Math.ceil(prev + 65535.5) // isLastCard
                : prev ? next / 2 // isFirstCard
                    : 65535;
    };
    return FakeListAPI;
}());
var Demo = /** @class */ (function () {
    function Demo() {
        this.listApi = new FakeListAPI(); // usually injected or set in constructor
    }
    Demo.prototype.run = function () {
        var _this = this;
        this.listApi.getLists().then(function (lists) {
            _this.lists = lists;
            var boards = lists
                .map(function (l) { return _this.listToKanbanBoard(l); })
                .map(function (b) { return _this.processBoard(b); });
            var kanbanTest = _this.initKanban(boards, '#myKanban');
            $('.meta-tasks').each(function (i, el) { return new PerfectScrollbar(el, { useBothWheelAxes: true }); });
            _this.setupUI(kanbanTest);
        });
    };
    Demo.prototype.listToKanbanBoard = function (list) {
        var _this = this;
        var asIsProps = ['id', 'title'];
        var board = _.pick(list, asIsProps);
        board.item = list.cards.map(function (c) { return _this.cardToKanbanCard(c); });
        board.meta = _.omitBy(list, asIsProps.concat(['cards']));
        return board;
    };
    Demo.prototype.cardToKanbanCard = function (list) {
        var asIsProps = ['id', 'title'];
        var card = _.pick(list, asIsProps);
        card.meta = _.omitBy(list, asIsProps);
        return card;
    };
    Demo.prototype.processBoard = function (board) {
        // custom boards
        var _this = this;
        if (board.id === '_new') {
            board['class'] = 'new';
            board.meta = {
                icon: 'fas fa-inbox',
                boardActions: [
                    { icon: 'fas fa-sync', label: 'Synchronize' },
                    { icon: 'fas fa-filter', label: 'Filter' },
                    { icon: 'fas fa-sort-amount-down', label: 'Sort' },
                    { icon: 'fas fa-ellipsis-h', label: 'List Actions' },
                ]
            };
        }
        else if (board.id === '_todo') {
            board['class'] = 'info,good';
            board.dragTo = ['_working'];
            board.meta = {
                icon: 'far fa-calendar-check'
            };
        }
        else if (board.id === '_working') {
            board['class'] = 'warning';
            board.meta = {
                icon: 'fas fa-cogs'
            };
        }
        else if (board.id === '_done') {
            board['class'] = 'success';
            board.dragTo = ['_working'];
            board.meta = {
                icon: 'fas fa-check-double'
            };
        }
        // construct board dom html
        var $title = $("<div class=\"board-title\"></div>").text(board.title);
        if (board.meta && board.meta.icon) {
            $title.prepend("<i class=\"" + _.escape(board.meta.icon) + " fa-fw\"></i> ");
        }
        board.title = $title[0].outerHTML;
        if (board.meta.boardActions && board.meta.boardActions.length) {
            var $boardActions_1 = $('<div class="board-actions"></div>');
            board.meta.boardActions.map(function (ba) {
                var $button = $('<button class="action"></button>');
                if (ba.icon) {
                    var $icon = $("<i class=\"" + _.escape(ba.icon) + " fa-fw\"></i>");
                    $button.append($icon);
                }
                if (ba.label) {
                    $button.attr('title', ba.label);
                }
                return $button;
            }).forEach(function ($button) {
                $boardActions_1.append($button);
            });
            board.title += $boardActions_1[0].outerHTML;
        }
        if (board.item && board.item.length) {
            board.item.forEach(function (card) {
                card.parentId = board.id;
                _this.processCard(card);
            });
        }
        return board;
    };
    Demo.prototype.processCard = function (card) {
        var $title = $("<div class=\"card-title\"></div>");
        var genIcon = function (iconClass, title) {
            title = title ? " title=\"" + _.escape(title) + "\"" : '';
            return "<i class=\"" + _.escape(iconClass) + " fa-fw\"" + (title ? " title=\"" + _.escape(title) + "\"" : '') + "></i>";
        };
        var genAction = function (iconClass, title, href, label) {
            var $button = $(href ? '<a target="_blank"></a>' : '<button></button>');
            if (href) {
                $button.attr('href', href);
            }
            $button.addClass('action');
            var $icon = $("<i class=\"" + _.escape(iconClass) + " fa-fw\"></i>");
            $button.append($icon);
            if (label) {
                $button.append(label);
            }
            if (title) {
                $button.attr('title', title);
            }
            return $button;
        };
        var $cardActions = $('<div class="card-actions"></div>');
        if (card.meta) {
            if (card.meta.provider) {
                switch (card.meta.provider) {
                    case 'bitbucket':
                        $title.append(genIcon('fab fa-bitbucket text-info', 'BitBucket Issue'));
                        break;
                    case 'slack':
                        $title.append(genIcon('fab fa-slack text-warning', 'Slack Chat'));
                        break;
                    case 'zendesk':
                        $title.append(genIcon('fas fa-life-ring text-success', 'Zendesk Ticket'));
                        break;
                    case 'google-drive':
                        $title.append(genIcon('fab fa-google-drive text-info', 'Google Drive'));
                        break;
                    case 'trello':
                        $title.append(genIcon('fab fa-trello text-info', 'Trello'));
                        break;
                    case 'bitbucket':
                        $title.append(genIcon('fab fa-bitbucket text-info', 'BitBucket Issue'));
                        break;
                    case 'calendar':
                        $title.append(genIcon('far fa-calendar', 'Calendar'));
                        break;
                    case 'google-keep':
                        $title.append(genIcon('fas fa-sticky-note text-warning', 'Google Keep'));
                        break;
                    case 'bitbucket':
                        $title.append(genIcon('fab fa-bitbucket text-info', 'BitBucket Issue'));
                        break;
                    case 'email':
                        $title.append(genIcon('fas fa-at text-danger', 'Email'));
                        break;
                }
            }
            if (card.meta.type) {
                switch (card.meta.type) {
                    case 'bug':
                        $title.append(genIcon('fas fa-bug text-danger', 'Bug'));
                        break;
                    case 'comment':
                        $title.append(genIcon('fas fa-comment text-muted', 'Comment'));
                        break;
                    case 'comment-private':
                        $title.append(genIcon('fas fa-comment text-danger', 'Private Comment'));
                        break;
                    case 'comment-public':
                        $title.append(genIcon('fas fa-comment text-info', 'Public Comment'));
                        break;
                    case 'ticket':
                        $title.append(genIcon('fas fa-comment text-muted', 'Ticket'));
                        break;
                    case 'notification':
                        $title.append(genIcon('fas fa-exclamation-circle text-muted', 'Notification'));
                        break;
                    case 'card':
                        $title.append(genIcon('far fa-clone text-muted', 'Card'));
                        break;
                    case 'board':
                        $title.append(genIcon('far fa-columns text-muted', 'Board'));
                        break;
                    case 'folder':
                        $title.append(genIcon('fas fa-folder text-muted', 'Folder'));
                        break;
                    case 'calendar-event':
                        $title.append(genIcon('fas fa-calendar-alt text-muted', 'Event'));
                        break;
                    case 'note':
                        $title.append(genIcon('far fa-sticky-note text-muted', 'Note'));
                        break;
                    case 'message':
                        $title.append(genIcon('far fa-envelope text-muted', 'Note'));
                        break;
                }
            }
            if (card.meta.prefix) {
                $title.append("<strong>[" + _.escape(card.meta.prefix) + "]</strong>");
            }
            if (card.meta.editUrl) {
                $cardActions.append(genAction('fas fa-edit', 'Edit', card.meta.editUrl));
            }
            if (card.meta.viewUrl) {
                $cardActions.append(genAction('fas fa-eye', 'View', card.meta.viewUrl));
            }
            // append default actions - probably should also be context sensitive
            $cardActions.append(genAction('fas fa-check text-success', 'Accept', null, "<span class=\"text-success\">" + 'Accept' + "</span>"));
            $cardActions.append(genAction('fas fa-times text-danger', 'Decline'));
            $cardActions.append(genAction('fas fa-ellipsis-v', 'More...'));
        }
        $title.append(' ' + _.escape(card.title));
        if ($cardActions.length) {
            $title.append($cardActions);
        }
        card.title = $title[0].outerHTML;
        // tags
        var $cardTags = $('<div class="card-tags"></div>');
        if (card.meta) {
            if (card.meta.dueDate) {
                var dateStr = new Intl.DateTimeFormat('en-US', { month: 'short', day: 'numeric' }).format(card.meta.dueDate);
                $cardTags.append("<span class=\"badge badge-danger\" title=\"" + _.escape('This card has a due date.') + "\"><i class=\"far fa-clock\"></i> " + dateStr + "</span>");
            }
            if (card.meta.hasDescription) {
                $cardTags.append("<span class=\"badge badge-light text-muted\" title=\"" + _.escape('This card has a description.') + "\"><i class=\"fas fa-align-left\"></i></span>");
            }
            if (card.meta.isWatching) {
                $cardTags.append("<span class=\"badge badge-light text-muted\" title=\"" + _.escape('You are watching this card for changes.') + "\"><i class=\"fas fa-eye\"></i></span>");
            }
            if (card.meta.hasComments) {
                $cardTags.append("<span class=\"badge badge-light text-muted\" title=\"" + _.escape('This card has comments.') + "\"><i class=\"fas fa-comment\"></i></span>");
            }
            if (card.meta.tags && card.meta.tags.length) {
                var tagsToDisplay = 2;
                for (var i = 0; i < card.meta.tags.length && i < tagsToDisplay; i++) {
                    var $tag = $("<span class=\"badge badge-secondary\"></span>");
                    $tag.text(card.meta.tags[i]);
                    $cardTags.append($tag);
                }
                if (card.meta.tags.length > tagsToDisplay) {
                    var extraTags = card.meta.tags.length - tagsToDisplay;
                    var $extraTags = $("<span class=\"text-muted\">+" + extraTags + " more</span>");
                    $cardTags.append($extraTags);
                }
            }
        }
        if ($cardTags[0].hasChildNodes()) {
            card.title += $cardTags[0].outerHTML;
        }
        return card;
    };
    Demo.prototype.findCard = function (id) {
        for (var l = 0; l < this.lists.length; l++) {
            var list = this.lists[l];
            for (var i = 0; i < list.cards.length; i++) {
                var card = list.cards[i];
                if (card.id === id) {
                    return card;
                }
            }
        }
        return undefined;
    };
    Demo.prototype.initKanban = function (boards, jKanbanElemSelector) {
        var _this = this;
        var isDraggingCard = false;
        var kanban = new jKanban({
            element: jKanbanElemSelector,
            gutter: '5px',
            widthBoard: '320px',
            dragBoards: false,
            click: function (el) {
                _this.listApi
                    .getCard(el.dataset.eid)
                    .then(_this.openCardModal.bind(_this));
            },
            dragEl: function (el) {
                $(el).addClass('dragging');
                isDraggingCard = true;
            },
            dragendEl: function (el) {
                $(el).removeClass('dragging');
                isDraggingCard = false;
            },
            dropEl: function (el) {
                var $el = $(el);
                $el.closest('.kanban-drag')[0]._ps.update();
                var card = _this.findCard(el.dataset.eid);
                var prevCardOrder = null;
                var prevCard = $el.prev('.kanban-item')[0];
                if (prevCard) {
                    prevCardOrder = _this.findCard(prevCard.dataset.eid).order;
                }
                var nextCardOrder = null;
                var nextCard = $el.next('.kanban-item')[0];
                if (nextCard) {
                    nextCardOrder = _this.findCard(nextCard.dataset.eid).order;
                }
                card.order = FakeListAPI.genCardOrder(prevCardOrder, nextCardOrder);
            },
            addItemButton: false,
            boards: boards
        });
        // avoid propagation of card action button clicks
        $(kanban.element).find('.card-actions .action').on('click', function (e) {
            e.stopPropagation();
        });
        // auto-scroll list on card drag
        $('body').on('mousemove', function (e) {
            if (isDraggingCard) {
                if (e.target.parentElement && e.target.parentElement.dataset.eid) {
                    var cardId = e.target.parentElement.dataset.eid;
                    var $card = $(kanban.element).find(".kanban-item[data-eid=\"" + cardId + "\"]");
                    var kanbanDrag = $card.closest('.kanban-drag')[0];
                    var dragRect = kanbanDrag.getBoundingClientRect();
                    var top = dragRect.y;
                    var y = e.clientY;
                    if (y < top + 20) {
                        kanbanDrag.scrollBy(0, -20);
                    }
                    else if (y < top + 60) {
                        kanbanDrag.scrollBy(0, -10);
                    }
                    else if (y > top + dragRect.height - 20) {
                        kanbanDrag.scrollBy(0, 20);
                    }
                    else if (y > top + dragRect.height - 60) {
                        kanbanDrag.scrollBy(0, 10);
                    }
                }
            }
        });
        // auto-resize scrollbars on list resize
        var ro = new ResizeObserver(function (entries) {
            // TODO: throttle this
            for (var _i = 0, entries_1 = entries; _i < entries_1.length; _i++) {
                var entry = entries_1[_i];
                entry.target._ps.update();
            }
        });
        $('.kanban-drag').each(function (i, el) {
            el._ps = new PerfectScrollbar(el, { useBothWheelAxes: true });
            ro.observe(el);
        });
        $('.kanban-board > footer').append("<a href=\"#\"><i class=\"fas fa-plus small\"></i> Adicionar outro card</a>");
        // TODO: add card button - needs functionality, styling and multiple positions
        $('.kanban-board > footer a').on('click', function (e) {
            e.preventDefault();
            var $board = $(e.target).closest('.kanban-board');
            var $drag = $board.find('.kanban-drag');
            $drag.append("\n<div class=\"kanban-item\">\n  <form>\n    <textarea name=\"title\" style=\"width: 100%;border: none;outline: none; resize: none; padding: 0;\"></textarea>\n  </form>\n</div>\n");
        });
        return kanban;
    };
    Demo.prototype.setupUI = function (kanbanTest) {
        // todo
    };
    /* TODO: creates and opens a modal dialog for the full card object */
    Demo.prototype.openCardModal = function (card) {
        console.warn('Stub(openCardModal): ' + 'Dialog is unimplemented!'); // debug
        // prepare modal dom
        $('#cardModalTitle').text(card.title);
        $('#debug-modal-model').text(JSON.stringify(card, null, 2));
        // open modal
        var options = {};
        $('#cardModal').modal(options);
    };
    return Demo;
}());
new Demo().run();